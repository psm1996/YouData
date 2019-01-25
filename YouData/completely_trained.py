import pandas as pd
import re
import nltk
import requests
import urllib.request  
import re
import nltk
from urllib.parse import urlparse
from nltk.corpus import stopwords
from nltk.stem.porter import PorterStemmer
  
dataset = pd.read_csv('abcd.txt',delimiter='\t',quoting = 3,encoding = "ISO-8859-1")
corpus = []
unstemmed = []
DANDELION_APP_ID = '357ae844c009454c81015445f6d2cd64'
DANDELION_APP_KEY = '357ae844c009454c81015445f6d2cd64'
ENTITY_URL = 'https://api.dandelion.eu/datatxt/nex/v1'

def get_entities(text, confidence=0.2, lang='en'):
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
    
for i in range(0,815):
    review = re.sub('[^a-zA-Z]',' ',dataset['commentText'][i])
    review = review.lower()
    review = review.split()
    lemma = nltk.stem.WordNetLemmatizer()
    ps = PorterStemmer()
    review = [word for word in review if not word in set(stopwords.words('english'))]
    review = ' '.join(review)
    corpus.append(review)
    
from sklearn.feature_extraction.text import CountVectorizer
cv = CountVectorizer(max_features= 700)
X = cv.fit_transform(corpus).toarray()
y = dataset.iloc[:,1:2].values

# strings = input(':')
strings =pd.read_csv('C:\\Users\\soumy\\Desktop\\CommentsDataset.txt',delimiter='\t',quoting = 3,encoding = "ISO-8859-1")
# print(strings['Comment'][0])
# strings.drop('videoID',inplace=True,axis=1)
check = []
for i in range(0,len(strings)):
	review = re.sub('[^a-zA-Z]',' ',strings['Comment'][i])
	review = review.lower()
	review = review.split()
	review = [word for word in review if not word in set(stopwords.words('english'))]
	review = ' '.join(review)
	check.append(review)
		
# print(check)
X_test = cv.transform(check).toarray()
# print(X_test)
# Fitting classifier to the Training set
# Create your classifier here
from sklearn.ensemble import RandomForestClassifier 
classifier = RandomForestClassifier(n_estimators = 40, criterion = 'entropy',random_state = 0)
classifier.fit(X,y)

y_pred = classifier.predict(X_test) 
# print(y_pred)
result=[]
for i in range(0,len(y_pred)):
	# print(check[i])
	if y_pred[i]==1:
		
		strings = re.sub('[^a-zA-Z]',' ',check[i])
		strings = strings.lower()
		strings = strings.split()
		strings = [word for word in strings if not word in set(stopwords.words('english'))]
		strings = ' '.join(strings)
		response = get_entities(strings)
		lists = response['annotations']
		for i in range(0,len(lists)):
			dictionary = lists[i]
			if(dictionary['confidence'] >= 0.5):
				result.append(dictionary['spot'])
			
				# print('')
for i in range(0,len(result)):
	print(result[i])
# print(result)
    	
    	
    	
    	
        	
            	
    
	
    	

"""from sklearn.metrics import accuracy_score
accuracy = accuracy_score(y,y_pred_new)"""

#Making the Confusion Matrix
"""from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_test, y_pred)"""









