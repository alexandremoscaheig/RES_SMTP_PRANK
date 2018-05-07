# SMTP Prank
## Description
This program is a (TCP) client application written in Java who communicate through the Java Socket API with a SMTP server. 
This application choose prompt messages to be choosen by the user and then send this message to all groups emails(victims) in the xml file (see below).

the xml file is format like that:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<smtp_prank>
	<groups>
		<group>
			<name>Lenny group</name>
			<from value="lenny.aebischer@heig-vd.ch">Lenny Aebischer</from>
			<dests>
				<dest value="alexandre.mosca@heig-vd.ch">Alexandre Mosca</dest>
				<dest value="maxime.vulliens@heig-vd.ch">Maxime Vuillens</dest>
			</dests>
		</group>
		<group>
			<name>My super group 1 </name>
			<from value="alexandre.mosca@heig-vd.ch">Alexandre Mosca</from>
			<dests>
				<dest value="lenny.aebischer@heig-vd.ch">Lenny Aebischer</dest>
				<dest value="maxime.vulliens@heig-vd.ch">Maxime Vuillens</dest>
			</dests>
		</group>
	</groups>
	<messages>
		<message>
			<subject>Mon message cool</subject>
			<body>
        Bonjour
			</body>
		</message>
		<message>
			<subject>Mon autre message moins cool </subject>
			<body>Aurevoir merci bonjour.</body>
		</message>
	</messages>
</smtp_prank>
```

## Run
To run the application, it required 3 arguments:
1: Hostname of the SMTP Server
2. Port of the SMTP Server
3. Name of the xml file (in the same directory as the executable)
