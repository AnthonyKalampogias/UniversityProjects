#This program is to get a total of how many links are included in the given html page and how many new lines
import urllib2
url=line=0

page = urllib2.urlopen(raw_input("Please give the exact URL of the page: "))
page= page.read()
for i in range(len(page)):
    if page[i] =="<":
        if page[i+1] =="a":
            url+=1
        elif page[i+1] == "p":
            line+=1
        elif page[i+1] == "b":
            if page[i+2] == "r":
                if page[i+3] == ">":
                    line +=1
print "The total amount of links in this page is: ",url
print "Total lines changed eiter by <br> or p>",line
