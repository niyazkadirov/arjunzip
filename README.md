# Extracting archives type ARJ

**1. Archive creation**

If you use the utility for compressing [ARJ Software](http://www.arjsoftware.com/files.htm), then use the command for compression ``ARJ32 a -m0 archive *``
  [more details here.](https://www.mankier.com/1/arj)

**2. How to use**

The class **ArjArchive** has one single **toExtract** method,
where you pass the path to the **.arj** file as the first parameter, where you want to extract the second parameter

**Important**    

Simple extraction of archives with the extension .arj, the file must be **uncompressed**.
