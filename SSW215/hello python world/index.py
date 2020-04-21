import requests
import json

def APIResponse(): #prints the GPS coordinates of the ISS 
    url = 'http://api.open-notify.org/iss-now.json'
    
    response = requests.get(url)
    data = response.json()

    lat = data["iss_position"]["latitude"]
    long = data["iss_position"]["longitude"]

    issLocation = "Coordinates of ISS: (" + lat + ", " + long + (")")

    print(issLocation)

APIResponse()

