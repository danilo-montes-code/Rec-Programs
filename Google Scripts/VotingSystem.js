//--------------------------------------------------------------------------------------------------------------------------//
//------------------------------------/Google Sheets Voting System by Danilo Montes/----------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------//


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
    //------------------------------/Change to ui.createAddonMenu() when publishing as add-on/------------------------------//
    ui.createMenu('Voting System')
    // Applies to early sheet setup
    .addItem('Create Sheet Headers','createHeaders')
    .addItem('Make New Spreadsheet','copySpreadsheet')
    .addItem('Make New Sheet with Same Headers', 'duplicateSheetAndHeaders')
    .addSeparator()
    // Applies to making modifications for the entire sheet
    .addItem('Clear Header Row','clearHeaders')
    .addItem('Change All Column Widths','setWidths')
    .addSeparator()
    // Applies to making modifications for the header columns only
    .addItem('Change Header Widths','resizeHeaders')
    .addItem('Change Header Font Size', 'changeHeaderFontSize')
    .addSeparator()
    // Applies to the voting cells
    .addItem('Delete Sheet Rows', 'delRows')
    .addItem('Make Voting Cells','makeVotingCells')
    .addSeparator()
    // Applies to info about the script
    .addItem('Voting System Info', 'info')
    .addToUi();
  }
  ui.createMenu('Vote').addItem('Vote', 'vote').addItem('How to Vote', 'voteInfo').addToUi();  // Vote Menu
  ui.createMenu('Authorization').addItem('Give Edit Permission', 'onOpen').addToUi();  // Authorization Menu
  //ui.createMenu('Temporary Functions').addItem('Delete Sheets','delSheets').addToUi();  // Temporary Functions Menu
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



  // Creates copy of the spreadsheet
  function copySpreadsheet() {
    var newSS = ui.prompt('What do you want to name the new Spreadsheet?')
    if (newSS.getSelectedButton() == ui.Button.OK)
      ss.copy(newSS.getResponseText());
    ui.alert('New spreadsheet entitled "'+newSS.getResponseText()+'" has been created. Go to either your drive or the Google Sheets home page to open it.');
  }


  // Creates a new sheet with the same headers as the active sheet
  function duplicateSheetAndHeaders() {
    var resp = ui.prompt('How many sheets to you want to make?');
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
      var headers = getHeadRow();
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
    var size = ui.prompt('What size font do you want the header column to be?');
    if (size.getSelectedButton() == ui.Button.OK) {
      var headers = getHeadRow();
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


  // Deletes unnecessary rows
  function delRows() {
    var resp = ui.prompt('Enter the last row to be kept on the sheet.');
    while (true) {
      if (resp.getSelectedButton() == ui.Button.OK) {
        if (!isNaN(parseInt(resp.getResponseText()))) {
          if (parseInt(resp.getResponseText()) < sheet.getMaxRows())
          sheet.deleteRows(parseInt(resp.getResponseText()), sheet.getMaxRows()-parseInt(resp.getResponseText()));
          break;
        } else {
          if (resp.getResponseText() != '') {
            ui.alert('Please enter the last row\'s number.');
            resp = ui.prompt('Enter the last row to be kept on the sheet.');
          } else
            break;
        }
      } else
        break;
    }
  }


  // Makes the upvote and downvote columns
  function makeVotingCells() {
    var goOrNo = ui.alert('It is highly recommended that you delete unnecessary rows before you make the voting cells.',
                          'If you have already deleted unnecessary rows, click "OK." If you haven\'t done so, click either "CANCEL" or the X button in the top right.\n\nNumber of rows in this sheet: '+sheet.getMaxRows().toString(),
        ui.ButtonSet.OK_CANCEL);
    if (goOrNo == ui.Button.OK) {
      var headers = getHeadRow();
      for (col = 1; col <= sheet.getMaxColumns(); col++) {
        header = sheet.getRange(1, col).getValue();

        // If the header is neither empty nor has 'Voting' in the box
        if (header != '' && header != 'Voting') {
          var responseRows = getResponseRowAmnt(col);

          // If there is something under the header
          if (responseRows.length != 0) {
            if (sheet.getRange(1, col + 1).getValue() != 'Voting') { // Prevents a second voting layer if there is already one

              // Makes the three voting columns
              for (i = 0; i < 3; i++)
                sheet.insertColumnAfter(col);

              // Sets the values in the voting columns
              sheet.getRange(1, col+1, 1, 2).merge();
              sheet.getRange(1, col+1).setValue('Voting').setHorizontalAlignment('center');
              sheet.setColumnWidth(col+3, 5);
            }

            // Puts the voting columns next to the cells that are filled
            for (len = 0; len < responseRows.length; len++) {
              var rowPlacer = responseRows[len];
              if (sheet.getRange(rowPlacer, col + 1).getFormula() != '=IMAGE("http://i67.tinypic.com/zwcexc.jpg", 4, 20, 20)') {
                sheet.getRange(rowPlacer, col + 1).setValue('=IMAGE("http://i67.tinypic.com/zwcexc.jpg", 4, 20, 20)').setHorizontalAlignment("center").setVerticalAlignment("middle"); // Upvote
                sheet.getRange(rowPlacer, col + 2).setValue('=IMAGE("http://i67.tinypic.com/23vi253.jpg", 4, 20, 20)').setHorizontalAlignment("center").setVerticalAlignment("middle"); // Downvote
                sheet.setRowHeight(rowPlacer, 30);
              }
            }

            // If the row that the last bit of data is in is greater than the last row that the grey background extends to, extends the grey to the end of the data
            if (responseRows[responseRows.length-1] > lastGreyRow(col + 3))
            sheet.getRange(1, col+3, responseRows[responseRows.length-1]).setBackground('grey');
            // If the row that the last bit of data is in is less than the last row that the grey background extends to, shortens the grey to the end of the data
            else if (responseRows[responseRows.length-1] < lastGreyRow(col + 3))
            sheet.getRange(responseRows[responseRows.length-1]+1, col+3, lastGreyRow(col + 3)).setBackground('white');

            sheet.setColumnWidths(col + 1, 2, 30);
            sheet.setColumnWidth(col + 3, 5);
          } else {
            if (sheet.getRange(1, col + 1).getValue() == 'Voting')
              sheet.deleteColumns(col + 1, 3);
          }
        } // Close voting or empty if
      }
    }
  }


  //--------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------/Info/--------------------------------------------------------------//
  //--------------------------------------------------------------------------------------------------------------------------//


  // Gives info about how the Voting System works
  function info() {
    ui.alert('Welcome to the Voting System!\n\n' +
             'This add-on allows users to enter responses under headers for evaluation by the other editors of the document. '+
             'Larger font size indicates more upvotes, and smaller font size indicates more downvotes. '+
             'To see how to vote, click \'How to Vote\' under the \'Vote\' menu on the ribbon.\n\n\n\n'+

             'Descriptions of the "Voting System" menu options\n\n'+
             '"Create Sheet Headers" - Freezes the top/header row and asks you to set what you want to have as your headers. It then asks you to set the alignment of the text and the size of the columns.\n'+
             '"Make New Spreadsheet" - Prompts you to name a new spreadsheet, which will be an exact copy of the current spreadsheet. (Note: The new spreadsheet will require you and all editors to click "Give Edit Permission" under the "Authorization" menu again on the new spreadsheet).\n'+
             '"Make New Sheet with Same Headers" - Prompts you to indicate how many new sheets you want to create, and creates that many new sheets, all with the same headers as the initial sheet.\n'+

             '"Clear Header Row" - Makes the entire first row blank.\n'+
             '"Change All Column Widths" - Prompts you to provide a size for all the columns in the sheet to be.\n'+

             '"Change Header Widths" - Prompts you to provide a size for only the columns with headers to be, excludes "Voting" and empty cells for adjustment.\n'+
             '"Change Header Font Size" - Changes the font size of only the header columns that you have placed text into, excludes "Voting" and empty cells for adjustment.\n'+

             '"Delete Sheet Rows" - Deletes all the rows after the row that the user gives. *HIGHLY RECOMMENDED BEFORE MAKING VOTING CELLS*\n'+
             '"Make Voting Cells" - This creates the upvote and downvote columns that will allow everyone to vote (increase and decrease the font size). This can be pressed at any time and will update the voting columns to fit the data in the sheet when it is pressed.\n'+

             '"Voting System Info" - Gives this info.\n\n\n'+


             'Descriptions of the "Vote" menu options\n\n'+
             '"Vote" - Vote on the text to the left of the vote arrow.\n'+
             '"How to Vote" - Describes how to vote and what voting does.\n\n\n'+


             'Description of the "Authorization" menu option\n\n'+
             '"Give Edit Permission" - Gives the script edit permission for the document (needed for the voting system to work).\n\n\n\n\n'+



             'If you have any questions or requests, ask me and I will try my best to deliver!\n'+
             '- Danilo Montes\n'+
             'danilo.montes101@gmail.com'
            );
  }


  // Gives info about how to vote
  function voteInfo() {
    ui.alert('In order to vote, select the cell of with the vote arrow of the vote that you want to perform. '+
             'Then, either click \'Vote\' option under the \'Vote\' menu on the ribbon or press ctrl+alt+shift+1.\n\n'+
             'The upvotes and downvotes increase and decrease the font size of the text to the left respectively.')
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
  function getHeadRow() {
    return sheet.getSheetValues(1, 1, 1, sheet.getMaxColumns());
  }


  // Gives the data of the header row that is neither empty nor 'Voting'
  function getTextHeaders() {
    var textHeaders = [[]];
    var head = getHeadRow();
    head[0].forEach(function(header) {
      if (header != '' && header != 'Voting')
        textHeaders[0].push(header);
    });
    return textHeaders;
  }


  // Returns the last row that grey background in
  function lastGreyRow(column) {
    var lastRow = 1;
    while (true) {
      if (sheet.getRange(lastRow, column).getBackground() != '#808080')
        break;
      lastRow++;
    }
    return lastRow;
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
  SpreadsheetApp.flush();
}


//--------------------------------------------------------------------------------------------------------------------------//
//---------------------------------------------------/Temporary Menu/-------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------//


  // Deletes sheets in the given number range
  function delSheets() {
    var resp = parseInt(ui.prompt('Number of sheets to keep (have to be in the front of the list to be saved).').getResponseText());
    var sheets = ss.getSheets();
    for (she = resp; she < sheets.length; she++)
      ss.deleteSheet(sheets[she]);
  }
