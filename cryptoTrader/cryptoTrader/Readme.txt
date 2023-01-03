System Implementation

by Project Groups 22
Ryan Douglas Daer (rdaer2) , Chang Hui Hou (chou24) ,
Inderjit Singh (iinderji) , Maxime Savehilaghi (msavehil) 

important file location:

	loginUI is located at srs/main/java/cryptoTrader/Authentication/LoginUI
	run LoginUI to start the system, if sucessfully login, then the MainUI will run

	CredentialDB is located at outmost file
	here credentialDB is a txt file
	
	Sample Credentials to login into the UI
	
	Username : user1
	Password : password123

Configuration/Setting:

	Setting up for Eclipse:
	In the Project Explorer window, right click file "pom.xml", 
	and go to Maven > Update Project... 
	Then select project in the popup window and hit "ok"



Example flow:



Running the program:

1. Run LoginUI. 
Input ID and password until correctly login
2. MainUI is shown
3. Add couple valid new brokers with complete information
4. Remove any unwanted brokers
5. Perform trade by "perform trade" button
6. Trade strategy is performed based on given broker list and their information
7. Update trade action log and histogram based on the trade
8. Repeat 3-7 or 4-7 or 5-7 until exit input
9. End