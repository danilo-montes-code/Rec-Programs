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


//--------------------------------------------------------------------------------------------------------------------------//
//---------------------------------------------------/Ribbon Menus/---------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------//


// Runs when a person opens the spreadsheet
function onOpen() {
  // Creates the custom dropdown menu on the ribbon
  if (isAuthenticated()) {
    ui.createMenu('Voting System') //---------------------/Change to ui.createAddonMenu() when publishing as add-on/--------//
    .addItem('Create Sheet Headers','createHeaders')
    .addItem('Make New Sheet with Same Headers', 'duplicateSheetAndHeaders')
    .addSeparator()
    .addItem('Clear Header Row','clearHeaders')
    .addItem('Change All Column Widths','setWidths')
    .addSeparator()
    .addItem('Change Header Widths','resizeHeaders')
    .addItem('Change Header Font Size', 'changeHeaderFontSize')
    .addSeparator()
    .addItem('Make Voting Cells','makeVotingCells')
    .addSeparator()
    .addItem('Info', 'info')
    .addSeparator()
    .addItem('Del Sheets','delSheets')
    .addItem('Del Rows', 'delRows')
    .addToUi();
  }
  ui.createMenu('Vote')
  .addItem('Vote', 'vote')
  .addToUi()
}


//--------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------/Voting System Options/---------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------//


if (isAuthenticated()) { // Only gives access to the creation of the voting system to the owner of the spreadsheet


  //--------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------/First Options Section/---------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


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


    // Creates a new sheet with the same headers as the active sheet
  function duplicateSheetAndHeaders() {
    var resp = ui.prompt('How many sheets to you want to make?', ui.ButtonSet.OK);
    if (resp.getSelectedButton() == ui.Button.OK) {
      while (true) {
        var text = resp.getResponseText();
        if (text != '') {
          if (!isNaN(parseInt(text))) {
            for (num = 0; num < parseInt(text); num++) {
              // Gets the header data from the sheet to duplicate
              var headers = getTextHeaders();
              var headWidth = sheet.getColumnWidth(1);
              var headAlign = sheet.getRange(1,1).getHorizontalAlignment();
              var headSize = sheet.getRange(1,1).getFontSize();

              // Makes the new sheet and makes it active
              ss.insertSheet();
              sheet = pickSheet();

              // Sets the header row to match the header row of the initial sheet (w/o 'Voting' and empty spaces)
              sheet.setFrozenRows(1);
              var range = sheet.getRange(1,1,1, headers[0].length);
              range.setValues(headers);  // Header text
              sheet.setColumnWidths(1, sheet.getMaxColumns(), headWidth);  // Width
              sheet.getRange(1,1,1, sheet.getMaxColumns()).setHorizontalAlignment(headAlign);  // Alignment
              sheet.getRange(1,1,1, sheet.getMaxColumns()).setFontSize(headSize);  // Font size
            }
            break;
          } else {
            ui.alert('Please enter an integer number of sheets to create.');
            resp = ui.prompt('How many sheets to you want to make?', ui.ButtonSet.OK);
          }
        } else
          break;
      }
    }
  }


  //--------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------/Second Options Section/--------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


  // Makes the header row blank
  function clearHeaders() {
    var answer = ui.alert('Are you sure you want to delete ALL of the data on the header row?', ui.ButtonSet.YES_NO);
    if (answer == ui.Button.YES)
      sheet.getRange(1,1,1, sheet.getMaxColumns()).clear();
  }


    // Sets the size of all the columns in the entire sheet
  function setWidths() {
    var width = columnWidth(sheet.getColumnWidth(1));
    if (width != 0)
      sheet.setColumnWidths(1, sheet.getMaxColumns(), width);
  }


  //--------------------------------------------------------------------------------------------------------------------------//
  //-------------------------------------------------/Third Options Section/--------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


  // Resizes the headers that are not blank or for voting
  function resizeHeaders() {
    var columnCounter = 1;
    var width = columnWidth(sheet.getColumnWidth(1));
    if (width != 0) {
      var headers = getHeaders();
      headers[0].forEach(function(header) {
        if (header != '' && header != 'Voting')
          sheet.setColumnWidth(columnCounter, width);
        columnCounter++;
      });
    }
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


  //--------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------/Fourth Options Section/--------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


  // Makes the upvote and downvote columns
  function makeVotingCells() {
    var columnCounter = 1;
    var headers = getHeaders();
    headers[0].forEach(function(header) {
      // If the header is neither empty nor has 'Voting' in the box
      if (header != '' && header != 'Voting') {
        var responseRows = getResponseRowAmnt(columnCounter);
        // If there is something under the header
        if (responseRows.length != 0) {

          // Makes the three voting columns
          for (i = 0; i < 3; i++)
            sheet.insertColumnAfter(columnCounter);

          // Sets the values in the voting columns
          sheet.getRange(1, columnCounter+1, 1, 2).merge();
          sheet.getRange(1, columnCounter+1).setValue('Voting').setHorizontalAlignment('center');
          sheet.getRange(1, columnCounter+3, responseRows[responseRows.length-1]).setBackground('grey');
          sheet.setColumnWidth(columnCounter+3, 5);

          // Puts the voting columns next to the cells that are filled
          for (len = 0; len < responseRows.length; len++) {
            var rowPlacer = responseRows[len];
            sheet.getRange(rowPlacer, columnCounter + 1).setValue('=IMAGE("http://i67.tinypic.com/zwcexc.jpg", 4, 20, 20)').setHorizontalAlignment("center").setVerticalAlignment("middle"); // Upvote
            sheet.getRange(rowPlacer, columnCounter + 2).setValue('=IMAGE("http://i67.tinypic.com/23vi253.jpg", 4, 20, 20)').setHorizontalAlignment("center").setVerticalAlignment("middle"); // Downvote
            sheet.setRowHeight(rowPlacer, 30);
          }
          sheet.setColumnWidths(columnCounter + 1, 2, 30);
          sheet.setColumnWidth(columnCounter + 3, 5);
          columnCounter += 3;
        }
      } // Close voting or empty if
      columnCounter++;
    });
  }


  //--------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------/Info/--------------------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


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


  //--------------------------------------------------------------------------------------------------------------------------//
  //-----------------------------------------------------/Functions/----------------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


  // Aligns the entire header row to what the user inputs
  function headerAlign() {
    var position = ui.prompt('How do you want the headers aligned?', '(left/center/right)', ui.ButtonSet.OK);
    if (position.getSelectedButton() == ui.Button.OK) {
      while (true) {
        var pos = position.getResponseText();
        if (pos.toLowerCase() == 'left' || pos.toLowerCase() == 'center' || pos.toLowerCase() == 'right') {
          sheet.getRange(1,1,1,sheet.getMaxColumns()).setHorizontalAlignment(pos);
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


  // Input box for the column width
  function columnWidth(initialWidth) {
    var width = ui.prompt(
      'How many pixels wide do you want the columns to be?',
      '(Recommended: 250)',
      ui.ButtonSet.OK);
    if (width.getSelectedButton() == ui.Button.OK) { // If user clicks OK
      while (true) {
        var resp = width.getResponseText();
        if (resp != '') {
          if (isNaN(parseInt(resp))){  // If you entered a string
            ui.alert('Error: Please enter an integer number of pixels for the width of the columns.');
            width = ui.prompt(
              'How many pixels wide do you want the columns to be?',
              '(Recommended: 250)',
              ui.ButtonSet.OK);
          } else
            return parseInt(resp);
        }
        break;
      }
    }
    return 0;
  }


  // Marks the row numbers that have responses in them and returns an array of the row numbers
  function getResponseRowAmnt(column) {
    var rowSelector = [];
    for (row = 2; row <= sheet.getMaxRows(); row++) {
      var specRow = sheet.getRange(row, column).getValue();
      if (specRow != '')
        rowSelector.push(row);
    }
    return rowSelector;
  }


  // Gives the data of the header row
  function getHeaders() {
    return sheet.getSheetValues(1, 1, 1, sheet.getMaxColumns());
  }


  // Gives the data of the header row that is neither empty nor 'Voting'
  function getTextHeaders() {
    var textHeaders = [[]];
    var head = getHeaders();
    head[0].forEach(function(header) {
      if (header != '' && header != 'Voting')
        textHeaders[0].push(header);
    });
    return textHeaders;
  }


  //--------------------------------------------------------------------------------------------------------------------------//
  //-----------------------------------------------------/Temporary/----------------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


  // Deletes sheets in the given number range
  function delSheets() {
    var resp = parseInt(ui.prompt('Number of first sheets to keep').getResponseText());
    var sheets = ss.getSheets();
    for (she = resp; she < sheets.length; she++)
      ss.deleteSheet(sheets[she]);
  }


  // Deletes unnecessary rows
  function delRows() {
    var resp = parseInt(ui.prompt('Last row to be kept').getResponseText());
    sheet.deleteRows(resp, sheet.getMaxRows()-resp);
  }

} // End of Voting System Options


//--------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------/Vote/------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------//


// Everyone can access this part
// Vote based on what arrow you have selected
function vote() {
  var highlight = SpreadsheetApp.getCurrentCell();
  if (highlight.getFormula() == '=IMAGE("http://i67.tinypic.com/zwcexc.jpg", 4, 20, 20)') { // If upvote
    highlight.offset(0, -1).setFontSize(highlight.offset(0,-1).getFontSize() + 1);
  } else if (highlight.getFormula() ==  '=IMAGE("http://i67.tinypic.com/23vi253.jpg", 4, 20, 20)') { // If downvote
    highlight.offset(0, -2).setFontSize(highlight.offset(0,-2).getFontSize() - 1);
  }
}
