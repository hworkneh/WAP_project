Teams

Anene Terefe: 610773
Hanna Workneh: 610762

Configuration:

configure the database connection info in web.xml as follows
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
	



