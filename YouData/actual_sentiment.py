# -*- coding: utf-8 -*-
"""
Created on Sat Jan 26 05:50:59 2019

@author: soumy
"""
import pandas as pd

st = pd.read_csv('C:\\Users\\soumy\\Desktop\\CommentsDataset.txt',delimiter='\t',quoting = 3,encoding = "ISO-8859-1")
st1 = pd.read_csv('C:\\Users\\soumy\\Desktop\\ran.txt',delimiter='\t',quoting = 3,encoding = "ISO-8859-1")
                
grouped = st.groupby('videoId')

from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
analyser = SentimentIntensityAnalyzer()

def sentiment_analyzer_scores(sentence):
    score = analyser.polarity_scores(sentence)
    return score["compound"]
z=[]

"""dataset = pd.read_csv('abcd.txt',delimiter='\t',quoting = 3,encoding = "ISO-8859-1")"""

for x in st['Comment']:
  z.append(sentiment_analyzer_scores(x))

st['sentiment_score']=z
st['score']=(st['likeCount']*100)+(st['totalReplyCount']*10)+(st['sentiment_score']*1000)

w=st.groupby('videoId').sum().sort_values('score', ascending=False)
w=w.reset_index()  

w=w.head(n=5)

print(w['videoId'])
