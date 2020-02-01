//--------------------------------------------------------------------------------------------------------------------------//
//------------------------------------/Google Sheets GPA Calculator by Danilo Montes/---------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------//

// Sets the global spreadsheet and ui variables
var ss = SpreadsheetApp.getActiveSpreadsheet();
var sheet;
var ui = SpreadsheetApp.getUi();
pickSheet().getRange(1, 1, sheet.getMaxRows(), sheet.getMaxColumns());

// Sets the active sheet
function pickSheet() {
  sheet = SpreadsheetApp.getActiveSheet();
  return sheet;
}

// Makes the menu
function onOpen() {
  ui.createMenu("GPA Calculator")
  .addItem("Set Number of Lab Days", 'setLab')
  .addItem("Calculate GPA", 'calc')
  .addToUi();
}

// Sets the number of lab days, used in GPA calc
function setLab() {
  var lab = ui.prompt('How many lab days do you have?').getResponseText();
  while (isNaN(lab) || lab > 3 || lab < 0 || lab == '') {  // If entered text is not a number or not 0, 1, or 2
    ui.alert('Error: Please enter a valid number from 0 to 2');
    lab = ui.prompt('How many lab days do you have?').getResponseText();
  }
  sheet.getRange("K14").setValue(lab);
}

// Calculates the GPA and writes it on the sheet
function calc() {
  if (lab != '0' && lab != '1' && lab != '2')
    ui.alert('Error: Please set your number of lab days.');
  else {
    var gpaCell = sheet.getRange("H7");  // Where the GPA is going to be shown
    var gpa = calcGrades(calcWeights(getGrades()));  // Calculates the GPA
    gpaCell.setValue(gpa);  // Writes the GPA on the sheet
  }
}

// Gets the grades and weights (in string representation), putting them into a list 
function getGrades() {
  return [sheet.getRange("B5:C13").getValues(), sheet.getRange("D5:E13").getValues(), sheet.getRange("F5:G13").getValues()];
}

// Overwrites the weights column with their number equivalents
function calcWeights(grades){
  var shortenedGrades = [];
  var temp = [];
  
  grades.forEach(function(subset){  // For each course level
    subset.forEach(function(row){  // For each row
      if (row[0] == '' || row[1] == '') {  // Takes out row from data if unfilled or invalid
        if (isNaN(row[0]) || !isValidLetter(row[0]))  // TODO - Accept letter grades, make a new method that checks if it is a valid latter
          ui.alert(row[0] + ' is not a valid grade.');
      } else {
        var weight = row[1];  // String representation of the weight
        row[1] = numberWeight(weight);  // Converts to number representation of weight
        temp.push(row);
      }
    });
    shortenedGrades.push(temp);
    temp = [];
  });
  return shortenedGrades;
}

// Checks to see if the letter grade entered into the first row is a valid letter grade
function isValidLetter(grade) { 
  grade = grade.toUpperCase();
  return grade.equals("A+") || grade.equals("A") || grade.equals("A-") ||
    grade.equals("B+") || grade.equals("B") || grade.equals("B-") ||
      grade.equals("C+") || grade.equals("C") || grade.equals("C-") || 
        grade.equals("D+") || grade.equals("D") || grade.equals("D-") ||
          grade.equals("F");
}

// Converts the string representation of grade weights to numbers
function numberWeight(stringWeight) {
  if (stringWeight.toUpperCase() == "FY")  // 5 Credits for Full Year
    return 5.;
  else if (stringWeight.toUpperCase() == "S")  // 2.5 Credits for Semester
    return 2.5;
  else if (stringWeight.toUpperCase() == "Q")  // 1.25 Credits for Quarter
    return 1.25;
  else if (stringWeight.toUpperCase() == "SCI" )  // 5, 6, or 7 Credits, depending on number of lab days
    return 5. + parseInt(sheet.getRange("K14").getValue());
  else if (stringWeight.toUpperCase() == "GYM")  // 3, 4, or 5 Credits, depending on number of lab days
    return 5. - parseInt(sheet.getRange("K14").getValue());
  else {
    ui.alert('Error: '+stringWeight+' is not a valid option.');
    return 0.;
  }
}

// Calculates your GPA
function calcGrades(grades) {
  var ranks = [97.5, 91.5, 89.5, 85.5, 81.5, 79.5, 75.5, 71.5, 69.5, 65.5, 61.5, 59.5, 0];  // Lowest values of grades that achieve certain letters
  var letters = ["A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"];  // Letter grades in order
  var ap = [5.375, 5, 4.625, 4.125, 3.75, 3.375, 2.875, 2.5, 2.125, 1.625, 1.25, .875, .0];  // AP weights
  var honors = [4.945, 4.6, 4.255, 3.795, 3.45, 3.105, 2.645, 2.3, 1.955, 1.338, 1.15, .805, .0];  // Honors weights
  var academic = [4.3, 4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.7, 1.3, 1.0, .7, .0];  // Academic weights
  var allWeights = [ap, honors, academic];
  var numOfCredits = 0;  // Number of grades entered
  var total = 0;  // Total of grades w/ weights applied
  var count = 0;  // Shifts which weight set is being used
  
  grades.forEach(function(gradeSet) {  // Each course level
    var weights = allWeights[count];
    gradeSet.forEach(function(gradeSet) {  // Each row, 0 has grade, 1 has weight     
      numOfCredits += gradeSet[1];
      var i = 0;
      if (isNaN(gradeSet[0]))   // If there is a letter grade in the grade column
        i = letters.indexOf(grade[0]);
      else  // If there is a number grade in the grade column
        while (ranks[i] > gradeSet[0])  // Descends dowm the ranks until the correct letter is achieved
          i++;
      total += weights[i]*gradeSet[1];
    });
    count++;
  });
  return (total/numOfCredits).toFixed(3);
}
