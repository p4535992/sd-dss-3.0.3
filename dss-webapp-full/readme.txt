The both applications (DSS-Web and TLManager) are built as demonstration for a Windows environment and all required elements (like the JRE 1.6) are provided.

To start any application go to the folder matching your environment:
- Windows 32-bit: ./bin/32
- Windows 64-bit: ./bin/64

# For the web-application:
- Webapp-Startup.bat starts the Tomcat server
- Webapp-Shutdown.bat stops the Tomcat server

The HTML page is available via
  http://localhost:8080/dss-webapp/
Please make sure that your browser has the corresponding Java-plugin installed and activated.

Required callback-services for the applet are configured on two addresses
  http://localhost:8080/service/
  http://localhost:8080/dss-webapp/service/

The web-services (for e.g. integration in another application) are accessible via two addresses
  http://localhost:8080/wservice/
  http://localhost:8080/dss-webapp/wservice/
There are two SOAP services available, that are described (WSDL) when accessing one of the aforementioned URLs:
1. SignatureService
   http://localhost:8080/wservice/signatureService exposes three operations:
   - digestDocument
   - signDocument
   - extendSignature
   WSDL information is given via:
   - http://localhost:8080/dss-webapp/wservice/signatureService?wsdl
   - and (with more details) http://localhost:8080/dss-webapp/wservice/signatureService?wsdl=SignatureService.wsdl
2. ValidationService
   http://localhost:8080/wservice/validationService exposes one operation:
   - validateDocument
   WSDL information is given via:
   - http://localhost:8080/dss-webapp/wservice/validationService?wsdl
   - and (with more details) http://localhost:8080/dss-webapp/wservice/validationService?wsdl=ValidationService.wsdl

Note that the web-application connects to external internet addresses to fetch data (e.g. CRL/OCSP).
If you have a proxy, then you may want to use something like WebScarab.
When the application is started, the LoTL and its contained TSLs are fetched immediately.
It is quite "normal" that a TSL URL may not be accessible (e.g. from Portugal) and this error is logged on the console.

# For the TLManager:
- "TLManager-LOTL EU.bat" starts the TLManager in LOTL mode for EU administrations
- "TLManager-LOTL NONEU.bat" starts the TLManager in LOTL mode for other people
- "TLManager-TL EU.bat" starts the TLManager in TL mode for EU MS administrations
- "TLManager-TL NONEU.bat" starts the TLManager in TL mode for other people
- It is also possible to access the TLManager in one of the 4 modes via the web-application page.