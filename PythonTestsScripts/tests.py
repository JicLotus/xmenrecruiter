import json
import urllib.request
import urllib.parse


#Erase Data
f = urllib.request.urlopen('http://35.227.104.232:4567/eraseMutants')
print(f.read())  


#Sending Mutant Matrix
body = {'dna': ['AAAA', 'TTTT']}
myurl = "http://35.227.104.232:4567/mutant/"

###########################################
req = urllib.request.Request(myurl)
req.add_header('Content-Type', 'application/json; charset=utf-8')
jsondata = json.dumps(body)
jsondataasbytes = jsondata.encode('utf-8')
req.add_header('Content-Length', len(jsondataasbytes))
response = urllib.request.urlopen(req, jsondataasbytes) 
###########################################


#Asking for stats

f = urllib.request.urlopen('http://35.227.104.232:4567/stat')
print(f.read())   

