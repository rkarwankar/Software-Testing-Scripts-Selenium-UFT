Function ScreenshotFunc(description)
Dim screenshotPath
    Path = "D:\SQCM\Assignment 1\Screenshots\"  ' path for saving screenshot
    screenshotPath = Path & description & DataTable("iteration", dtGlobalSheet) & ".png"
    
    ' Desktop Screenshot Capture
    Desktop.CaptureBitmap screenshotPath, True
    Reporter.ReportEvent micDone, description, "Captured Screenshot: " & screenshotPath
End Function


SystemUtil.Run "C:\Program Files (x86)\OpenText\UFT One\samples\Flights Application\FlightsGUI.exe"

WpfWindow("OpenText MyFlight Sample").WpfEdit("agentName").Set DataTable("agentName", Global) @@ hightlight id_;_2117686168_;_script infofile_;_ZIP::ssf3.xml_;_
WpfWindow("OpenText MyFlight Sample").WpfEdit("password").SetSecure DataTable("password", Global) @@ hightlight id_;_2065644272_;_script infofile_;_ZIP::ssf7.xml_;_
wait(1)
'Screenshot 1- Before Signing in
ScreenshotFunc("Before signing")
WpfWindow("OpenText MyFlight Sample").WpfButton("OK").Click @@ hightlight id_;_2117689720_;_script infofile_;_ZIP::ssf6.xml_;_
wait(1)

'Screenshot 2- After Signing in
ScreenshotFunc("After signing")
' First Checkpoint Verification: Seattle to San Francisco is visible (BitMap Checkpoint)
WpfWindow("OpenText MyFlight Sample").WpfObject("Seattle to San Francisco,").Check CheckPoint("Seattle to San Francisco,  all inclusive") @@ hightlight id_;_1945472368_;_script infofile_;_ZIP::ssf37.xml_;_

' Second Checkpoint Verification: Tickets is visible (Text Checkpoint)
WpfWindow("OpenText MyFlight Sample").WpfObject("Tickets").Check CheckPoint("Tickets") @@ hightlight id_;_-258557464_;_script infofile_;_ZIP::ssf33.xml_;_

WpfWindow("OpenText MyFlight Sample").WpfComboBox("fromCity").Select DataTable("fromCity", Global)
WpfWindow("OpenText MyFlight Sample").WpfComboBox("toCity").Select DataTable("toCity", Global) @@ hightlight id_;_-260193040_;_script infofile_;_ZIP::ssf12.xml_;_
WpfWindow("OpenText MyFlight Sample").WpfImage("WpfImage_2").Click 4,8 @@ hightlight id_;_-254405304_;_script infofile_;_ZIP::ssf34.xml_;_
WpfWindow("OpenText MyFlight Sample").WpfCalendar("Mo").SetDate DataTable("datePicker", Global)
WpfWindow("OpenText MyFlight Sample").WpfComboBox("Class").Select DataTable("Class", Global) @@ hightlight id_;_1449620_;_script infofile_;_ZIP::ssf36.xml_;_
WpfWindow("OpenText MyFlight Sample").WpfComboBox("numOfTickets").Select DataTable("numOfTickets", Global) @@ hightlight id_;_2124587592_;_script infofile_;_ZIP::ssf18.xml_;_

wait(2)

'Screenshot 3- After Filling Flight Details 
ScreenshotFunc("After filling")

WpfWindow("OpenText MyFlight Sample").WpfButton("FIND FLIGHTS").Click @@ hightlight id_;_-260222080_;_script infofile_;_ZIP::ssf28.xml_;_

 @@ hightlight id_;_2124598096_;_script infofile_;_ZIP::ssf20.xml_;_
WpfWindow("OpenText MyFlight Sample").WpfTable("flightsDataGrid").SelectCell 0,1 @@ hightlight id_;_-151654440_;_script infofile_;_ZIP::ssf21.xml_;_
wait(2)
'Screenshot 4- Before Selecting Flight
ScreenshotFunc("Before selecting flight")

'Object added manually
WpfWindow("OpenText MyFlight Sample").WpfButton("SELECT FLIGHT").Click

' Third Checkpoint Verification: Flight Details is visible (Text Checkpoint)
WpfWindow("OpenText MyFlight Sample").WpfObject("FLIGHT DETAILS").Check CheckPoint("FLIGHT DETAILS")

' Fourth Checkpoint Verification: Check ticket count is 3 (Text Checkpoint)
WpfWindow("OpenText MyFlight Sample").WpfObject("3").Check CheckPoint("3")

WpfWindow("OpenText MyFlight Sample").WpfEdit("passengerName").Set DataTable("passengerName", Global)
wait(2)
'Screenshot 5- Before Placing Order
ScreenshotFunc("Before Placing Order")

WpfWindow("OpenText MyFlight Sample").WpfButton("ORDER").Click

'Screenshot 6- After placing order
ScreenshotFunc("After placing order")

WpfWindow("OpenText MyFlight Sample").Close @@ hightlight id_;_925680_;_script infofile_;_ZIP::ssf31.xml_;_
wait(2)



 @@ hightlight id_;_3019186_;_script infofile_;_ZIP::ssf39.xml_;_
