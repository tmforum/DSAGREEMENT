# AGREMENTMANAGEMENT-CTK
Installing and Running the Agreement Management CTK
The Agreement Management CTK is dependent on the installation of node.js and Newman to work.
The installation instructions for Newman are found here: https://www.getpostman.com/docs/newman_intro
Node.js can be downloaded and installed from:
http://nodejs.org/download/ 
Once Node.js and Newman are installed download and unzip the AgreementManagement-CTK ZIP file within your test directory.

You should see the following files:
-TMForumAgreementManagementAPITestCollectionV#.postman_collection : the postman collection for the Mandatory tests

Create a environment file TMFENV.

-TMFENV : the Environment variable for the REST API Endpoint

Open the TMVENV file and change the following host value to match your endpoint. Note that by default the environment is pointing to the Sandbox endpoint. 
    {
      "enabled": true,
      "key": "host",
      "value": "localhost:8080",
      "type": "text"
    }

now look for the AgreementManagementApi key and change it so that it matches the URL for the AgreementManagement resource:
   
    {
      "enabled": true,
      "key": "auth",
      "value": "user:user",
      "type": "text"
    },
    {
      "enabled": true,
      "key": "agreementBasePath",
      "value": "{{auth}}@{{host}}/DSAgreement/api/agreementManagement",
      "type": "text"
    }

Save the new values and exit.

Go to your test directory and type the following command:

> newman -c TMForumAgreementManagementAPITestCollectionV#.postman_collection -e TMFENV -H AgreementManagementCTKResult.html -o AgreementManagementCTKResult.json

where AgreementManagementCTKResult.html and AgreementManagementCTKResult.json will contain the results of the CTK execution. You should see something like the following example:

Iteration 1 of 1
request [object Object]
request typeobject
201 651ms POST  /agreement http://user:user@localhost:8080/DSAgreement/api/agreementManagement/agreeement
  ✔ Content-Type is present application/json
  ✔ Status code is 201
  ✔ Response contains ID 1234
  ✔ Response contains HREF
  ✔ POST Body Response equals Request Body

Parent                    Pass Count  FailCount
-------------------------------------------------------------
Folder agreement test         18         0
Folder hub test               8         0
Total                        26         0

If they are no failures then you have passed the CTK and your API is conformant with all
the Mandatory features.

The results of the CTK are also in  the AgreementManagementCTKResult.html
While all the information related to the execution of the CTK will be contained in the AgreementManagementCTKResult.json file


