// Sets the global spreadsheet and ui variables
var ss = SpreadsheetApp.getActiveSpreadsheet();
var sheet;
var ui = SpreadsheetApp.getUi();
pickSheet().getRange(1, 1, sheet.getMaxRows(), sheet.getMaxColumns()).setWrap(true);

// Sets the active sheet
function pickSheet() {
  sheet = SpreadsheetApp.getActiveSheet();
  return sheet;
}


// Sees if the user viewing the doc is the owner
function isAuthenticated() {
  return Session.getActiveUser().getEmail() == SpreadsheetApp.getActiveSpreadsheet().getOwner().getEmail();
}


function onOpen() {
  // Creates the custom dropdown menu on the ribbon
  if (isAuthenticated()) {
    ui.createMenu('Voting System')
    .addItem('Create Sheet Headers','createHeaders')
    .addItem('Make Voting Cells','makeVotingCells')
    .addSeparator()
    .addItem('Clear Sheet Headers','clearHeaders')
    .addItem('Resize Header Columns','resizeHeaders')
    .addItem('Change Header Font Size', 'changeHeaderFontSize')
    .addSeparator()
    .addItem('Info', 'info')
    .addToUi();
  }
  ui.createMenu('Vote')
  .addItem('Vote', 'vote')
  .addToUi()
}

if (isAuthenticated()) { // Only gives access to Soder and me (so I can test)
  // Gives the data of the header row
  function getHeaders () {
    return sheet.getSheetValues(1, 1, 1, sheet.getMaxColumns());
  }


  // Creates the headers
  function createHeaders() {
    ui.alert('Either click the X in the top right or click OK without anything in the input box to exit without modifying the option for all following prompts.');

    // Set the values we want for headers
    var headPieces = ui.prompt('Enter the headers for the columns, separated by commas with no spaces after the commas.',
                               'Do two commas in succession if you want an empty space (i.e. hello,,world)',
                               ui.ButtonSet.OK);
    if (headPieces.getSelectedButton() == ui.Button.OK && headPieces.getResponseText() != '') {
      // Freezes the first row
      sheet.setFrozenRows(1);

      var headers = headPieces.getResponseText().split(',');  // Separates the input by commas
      var values = [[]];
      // Puts each comma separated String into the 2D array that will set the headers
      headers.forEach(function(element) {
        values[0].push(element);
      });

      // Calculates the number of headers based on how many were put in
      numOfHeaders = values[0].length;

      // Set the range of cells
      range = sheet.getRange(1,1,1,numOfHeaders);

      // Call the setValues method on range and pass in our values
      range.setValues(values);

      headerAlign();
    }
  }


  // Aligns the header row to what the user inputs
  function headerAlign() {
    var position = ui.prompt('How do you want the headers aligned?', '(left/center/right)', ui.ButtonSet.OK);
    if (position.getSelectedButton() == ui.Button.OK) {
      while (true) {
        var pos = position.getResponseText();
        pos = pos.toLowerCase();
        var range = sheet.getRange(1,1,1,sheet.getMaxColumns());
        if (pos == 'left' || pos == 'center' || pos == 'right') {
          var align = [[]];
          for (x = 0; x < sheet.getMaxColumns(); x++) {
            align[0].push(pos);
          }
          range.setHorizontalAlignments(align);
          break;
        } else if (pos == ''){
          break;
        } else {
          ui.alert('Error: Please enter \'left\', \'center\', or \'right\'');
          position = ui.prompt('How do you want the headings aligned?', '(left,center,right)', ui.ButtonSet.OK);
        }
      }
      setWidths();
    }
  }


  // Sets the size of all the columns that have a header over them to be a certain number of pixels
  function setWidths() {
    var width = columnWidth(sheet.getColumnWidth(1));
    sheet.setColumnWidths(1, sheet.getMaxColumns(), width);
  }


  // Input box for the column width
  function columnWidth(initialWidth) {
    var width = ui.prompt(
      'How many pixels wide do you want the columns to be?',
      '(Recommended: 250)',
      ui.ButtonSet.OK);
    if (width.getSelectedButton() == ui.Button.OK) { // If user clicks OK
      while (true) {
        var resp = width.getResponseText();
        if (resp != "") {
          if (isNaN(parseInt(resp))){  // If you entered a string
            ui.alert('Error: Please enter a number of pixels for the width of the columns.');
            width = ui.prompt(
              'How many pixels wide do you want the columns to be?',
              '(Recommended: 250)',
              ui.ButtonSet.OK);
          } else
            return parseInt(resp);
        } else
          return initialWidth;
      }
    } else // If user clicks out of the prompt with x
      return initialWidth;
  }


  // Makes the header row blank
  function clearHeaders() {
    var answer = ui.alert('Are you sure you want to delete ALL headers?', ui.ButtonSet.YES_NO);
    if (answer == ui.Button.YES)
      sheet.getRange(1,1,1, sheet.getMaxColumns()).clear();
  }


  // Resizes the headers that are not blank or for voting
  function resizeHeaders() {
    var columnCounter = 1;
    var width = columnWidth(sheet.getColumnWidth(1));
    var headers = getHeaders();
    headers[0].forEach(function(header) {
      if (header != '' && header != 'Voting')
        sheet.setColumnWidth(columnCounter, width);
      columnCounter++;
    });
  }


  // Changes the size of the headers
  function changeHeaderFontSize() {
    var columnCounter = 1;
    var size = ui.prompt('What size font do you want the header column to be?', ui.ButtonSet.OK);
    if (size.getSelectedButton() == ui.Button.OK) {
      var headers = getHeaders();
      headers[0].forEach(function(header) {
        if (header != '' && header != 'Voting')
          sheet.getRange(1, columnCounter).setFontSize(parseInt(size.getResponseText()));
        columnCounter++;
      });
    }
  }


  // Makes the upvote and downvote columns
  function makeVotingCells() {
    var columnCounter = 2;
    var headers = getHeaders();
    headers[0].forEach(function(header) {
      if (header != '' && header != 'Voting') {
        var responseRows = getResponseRowAmnt(columnCounter-1);
        if (responseRows != 0) {
          for (i = 0; i < 3; i++)
            sheet.insertColumnAfter(columnCounter-1);
          sheet.getRange(1,columnCounter, 1, 2).merge();
          sheet.getRange(1,columnCounter, 1, 1).setValue('Voting').setHorizontalAlignment('center');

          for (row = 2; row <= responseRows; row++) {
            sheet.getRange(row,columnCounter, 1, 1).setValue('=IMAGE("http://i67.tinypic.com/zwcexc.jpg", 4, 20, 20)').setHorizontalAlignment("center").setVerticalAlignment("middle"); // Upvote
            sheet.getRange(row,columnCounter+1, 1, 1).setValue('=IMAGE("http://i67.tinypic.com/23vi253.jpg", 4, 20, 20)').setHorizontalAlignment("center").setVerticalAlignment("middle"); // Downvote
          }
          sheet.setColumnWidths(columnCounter, 2, 30);
          sheet.setRowHeights(2, responseRows, 30);
          sheet.setColumnWidth(columnCounter+2, 5);
          sheet.getRange(1, columnCounter+2, responseRows).setBackground('grey');
          columnCounter += 3;
        }
      }
      columnCounter++;
    });
  }


  // Returns the number of rows that have responses in them
  function getResponseRowAmnt(column) {
    var rows = 1;
    var numRows = 2;
    var col = sheet.getRange(numRows, column).getValue();
    if (col != '') {
      while (col != '') {
        numRows++;
        rows++;
        col = sheet.getRange(numRows, column).getValue();
      }
      return rows;
    } else
      return 0;
  }


  // Gives info to Soder about how it works
  function info() {
    ui.alert('Welcome to the Voting System!\n\n' +
             'I modeled this after your "Thesis Upvote Activity," trying to make it easier and more efficient for all parties involved. '+
             'I hope you like it and find use for it for your classes!\n\n\n\n'+
             'Descriptions of the menu options\n\n'+
             '"Create Sheet Headers" - This freezes the top/header row and asks you to set what you want to have as your headers.\n'+
             '"Make Voting Cells" - *ONLY CLICK THIS WHEN EVERYONE HAS PUT THEIR RESPONSES UNDER THE HEADINGS* This creates the upvote and down vote columns that will allow everyone to vote (increase and decrease the font size).\n'+
             '"Clear Sheet Headers" - Makes the top/header row blank.\n'+
             '"Resize Header Columns" - Resizes only the header columns that you have placed text into, excludes "Voting" and empty cells for adjustment.\n'+
             '"Change Header Font Size" - Changes the font size of only the header columns that you have placed text into, excludes "Voting" and empty cells for adjustment.\n'+
             '"Info" - Gives this info.\n\n'+
             'P.S. If you have any problems with the voting (if someone adds their response after you made the voting columns and you click "Make Voting Cells" again, the sheets will be a bit messed up), you can always add a new sheet on the bottom with the plus button and restart on another sheet.\n\n\n\n'+
             'If you have any questions or requests, ask me and I will try my best to deliver!\n'+
             '- Danilo Montes'
            );
  }
}




// Everyone can access this part
// Vote based on what arrow you have selected
/*highlight.offset(0,-1).getFontSize() + 1*/
function vote() {
  var highlight = SpreadsheetApp.getCurrentCell();
  if (highlight.getFormula() == '=IMAGE("http://i67.tinypic.com/zwcexc.jpg", 4, 20, 20)') { // If upvote
    highlight.offset(0, -1).setFontSize(highlight.offset(0,-1).getFontSize() + 1);
  } else if (highlight.getFormula() ==  '=IMAGE("http://i67.tinypic.com/23vi253.jpg", 4, 20, 20)') { // If downvote
    highlight.offset(0, -2).setFontSize(highlight.offset(0,-2).getFontSize() - 1);
  }
}
