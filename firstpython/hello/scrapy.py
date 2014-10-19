'''
Created on Jul 30, 2014

@author: zhwang1988
'''
import re
import urllib
  
def getHtml(url):
    page = urllib.urlopen(url)
    html = page.read()
    return html
  
def getImg(html):
    reg = r'src="(.+?\.jpg)" pic_ext'
    imgre = re.compile(reg)
    imglist = imgre.findall(html)
    x = 0
    l=len(imglist)
    print "%d pic"%(l)
    print "-------------------"
    for imgurl in imglist:
        print "%d pic" %(x+1)
        urllib.urlretrieve(imgurl,'/Users/zhwang1988/desktop' % x)
        x = x + 1      
     
html = getHtml("http://www.163.com")
getImg(html)
