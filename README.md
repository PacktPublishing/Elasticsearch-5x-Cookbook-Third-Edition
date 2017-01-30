# Elasticsearch-5x-Cookbook-Third-Edition
Code repository for Elasticsearch 5.x Cookbook, published by Packt
<<< # Elasticsearch-5.x-Cookbook

This is the code repository for[Elasticsearch 5.x Cookbook](https://www.packtpub.com/big-data-and-business-intelligence/elasticsearch-50-cookbook-third-edition?utm_source=github&utm_medium=repository&utm_campaign=9781786465580),published by Packt.It contains all the supporting
project files necessary to work through the book from start to finish.

## Instructions and Navigation
All of the code is organized into folders.Each folder starts with a number followed by the application name.
The commands and instructions will look like the following:

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

…
try {             // get URL content
  URL url = new URL("http://127.0.0.1:9200");             
  URLConnection conn = url.openConnection();// open the stream and put it into BufferedReader             
  BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));              

String inputLine;             
while ((inputLine = br.readLine()) != null){
System.out.println(inputLine);             
}             
br.close();              
System.out.println("Done");          
} catch (MalformedURLException e) {             e.printStackTrace();         
} catch (IOException e) {             
e.printStackTrace();         
}
In Scala, the request will be:
scala.io.Source.fromURL("http://127.0.0.1:9200",
"utf-8").getLines.mkString("\n")
For every language sample, the response will be the same:
{
  "ok" : true,
  "status" : 200,
  "name" : "Payge, Reeva",
  "version" : {
    "number" : "1.4.0",
    "snapshot_build" : false
  },
  "tagline" : "You Know, for Search"
}


## Note:
Chapter 01, 02, 10, 11 do not have code files.


## Related products:
* [ElasticSearch Cookbook](https://www.packtpub.com/big-data-and-business-intelligence/elasticsearch-cookbook?utm_source=github&utm_medium=repository&utm_campaign=9781782166627)
* [Mastering ElasticSearch 5.0 - Third Edition](https://www.packtpub.com/big-data-and-business-intelligence/mastering-elasticsearch-50-third-edition?utm_source=github&utm_medium=repository&utm_campaign=9781786460189)
* [Raspberry Pi Home Automation with Arduino](https://www.packtpub.com/hardware-and-creative/raspberry-pi-home-automation-arduino?utm_source=github&utm_medium=repository&utm_content=9781849695862
