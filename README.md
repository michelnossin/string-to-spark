# Usage


Create a Spark dataframe using a String, supports JSON and csv (comma seperated) strings. 
In case a Csv string has multiple lines the first line will be used as header.
# Examples

```
import com.nossin.spark.StringToSpark._

df"""{ "michel" : "nossin", "metadata": { "key": 84896, "value": 54 }}"""

df"jan,michel,kees jan"

df"""
    |id,date,timedump
    |1, "2014/01/01 23:00:01",1499959917383
    |2, "2014/11/31 12:40:32",1198138008843
  """
```

TODO : Strip any spaces in the csv header, as it appears they are added in the Spark column name
