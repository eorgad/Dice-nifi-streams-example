# Dice-nifi-streams-example
Multi threaded streams emulating dice data sent to web services for quick UI realtime analysis

Install HDF, use the following instructions
http://docs.hortonworks.com/HDPDocuments/HDF2/HDF-2.1.1/bk_dataflow-ambari-installation/bk_dataflow-ambari-installation-20161219.pdf

1.Start up Nifi UI and import the xml template (Multi-stream-dice-example.xml) to stream two threads of files creating random dice throuws.

Set up a local web service: You can set up you web services either on the a server or on your local mac for demo purposes.

2.1. Installation on CentOS server: To install apache, open terminal and type in this command:

sudo yum install httpd

2.2. Make configuration changes for your web services:

vi /etc/httpd/conf/httpd.conf

Place the content of the UI folder in the DocumentRoot location to be accessed via the webserver DocumentRoot "/var/www/html"

2.3. Start apache by running

sudo service httpd start

3.You can import the java project into eclipse or run the TwoCrapsTest from the cli to generate two files that Nifi would stream to your web instance. In the template there is a port that you can use to stream the feed via site-to-site to another Nifi instance, such as instance running on the edge node of your HDP instance (used HDP 2.5 sandbox VM for this example)



