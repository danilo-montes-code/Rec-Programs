import win32com.client

sheet_name = 'TestingSheet'
# opens sheet with win32 for formatting
excel = win32com.client.Dispatch("Excel.Application")
wb = excel.Workbooks.Add()
ws = excel.ActiveWorkbook
ws = excel.ActiveSheet
ws.Columns.Borders(11).LineStyle = 1

# Auto resizes the columns, centers the text, and bolds and yellows the top row
ws.Columns.AutoFit()
ws.Columns.Style.HorizontalAlignment = -4108
ws.Rows(1).Font.Bold = True

# sets the borders to make the sheet easier to read
ws.Rows(1).Borders.LineStyle = 1
ws.Columns.Borders(11).LineStyle = 1
# ws.Range(ws.Cells(2, 1), ws.Cells(last_row, last_col)).Sort(Key1=ws.Range(ws.Cells(2, 1), ws.Cells(last_row, 1)),
# Order1=1, Orientation=2)

# saves, gets the file path, and quits
ws.SaveAs(sheet_name+'.xlsx')
print(wb.Path+'\\'+sheet_name+'.xlsx')
excel.Application.Quit()
