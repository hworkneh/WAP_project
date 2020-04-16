Teams

Anene Terefe: 610773
Hanna Workneh: 610762

Configuration:

configure the mysql database connection info and Email sender options in web.xml as follows
	<context-param>
		<param-name>url</param-name>
		<param-value>jdbc:mysql://ip:port/</param-value>
	</context-param>
	<context-param>
		<param-name>database</param-name>
		<param-value>goldendomemarket</param-value>
	</context-param>
	<context-param>
		<param-name>user_name</param-name>
		<param-value>your database user name</param-value>
	</context-param>
	<context-param>
		<param-name>password</param-name>
		<param-value>your database password</param-value>
	</context-param>

// Email sending ... currenly configured to use gmail which allow less secure apps and  IMAP access. if not configured the application will still run without emailing functionality 
<context-param>
		<param-name>smtp_host</param-name>
		<param-value>smtp.gmail.com</param-value>
	</context-param>
	<context-param>
		<param-name>smtp_socketFactory_port</param-name>
		<param-value>465</param-value>
	</context-param>
	<context-param>
		<param-name>smtp_port</param-name>
		<param-value>465</param-value>
	</context-param>
	<context-param>
		<param-name>senderEmail</param-name>
		<param-value>enter email@gmail.com</param-value> 
	</context-param>
	<context-param>
		<param-name>senderEmailPassword</param-name>
		<param-value>enter gmail password</param-value>
	</context-param>
	
	
Then import the database goldendomemarket.sql

**Account credentials for admin
username= admin@miu.edu
password=A1234a


