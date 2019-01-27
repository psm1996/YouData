import requests

DANDELION_APP_ID = '357ae844c009454c81015445f6d2cd64'
DANDELION_APP_KEY = '357ae844c009454c81015445f6d2cd64'
 
ENTITY_URL = 'https://api.dandelion.eu/datatxt/nex/v1'
 
def get_entities(text, confidence=0.01, lang='en'):
    payload = {
        '$app_id': DANDELION_APP_ID,
        '$app_key': DANDELION_APP_KEY,
        'text': text,
        'confidence': confidence,
        'lang': lang,
        'social.hashtag': True,
        'social.mention': True
    }
    
    response = requests.get(ENTITY_URL, params=payload)
    return response.json()
 
def print_entities(data):
    for annotation in data['annotations']:
        print("Entity found: %s" % annotation['spot'])

if __name__ == '__main__':
    query = "Can you please make lecture on networking"
    response = get_entities(query)
    lists = response['annotations']
    for i in range(0,len(lists)):
        dictionary = lists[i]
        if(dictionary['confidence'] >=0.65 ):
            print(dictionary['spot'])
            
        
    