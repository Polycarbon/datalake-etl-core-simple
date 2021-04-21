# datalake-migration-check
Java MapReduce code to extract data from HBase in a following format

```
cif-acctNbr-dr  sumAmount
cif-acctNbr-cr  sumAmount
....
```

## How to use
### build
Clone this repo, go to root folder of this project, then run :
```
mvn clean package
```
You'll get 2 jar file inside /target. use the one with ```jar-with-dependencies.jar```

### run
Upload ```jar-with-dependencies.jar``` to one of Cloudera cluster, along with job-config.json from ```src/resoures/job-config.json```
- ```hbaseZookeeperQuorum``` : zookeeper's IP for HBase
- ```hbaseZookeeperPropertyClientPort``` : zookeeper's port for HBase
- ```outputPath``` : Path to store output in HDFS
- ```hbaseTableName``` : target E-Slip table name (include namespace)
- ```targetCifList``` : if you want to run this over entire table, make it empty string "", but if you want to target some CIF, put it here with comma-separated. 
- ```numReduceTask``` : number of reduce task (default 50)
- ```timerange``` : If specified, will get only record within [from,to-1]. If you don't want to set timerange, please delete this tag.
- ```dailyTargetDate``` : This is for daily reconcile, only select items which transaction_post_date equals to this config.
- ```newRegisterTargetDate``` : This is for new register reconcile, only select items which transaction_post_date <= this config.

ps. ```dailyTargetDate``` will override ```newRegiserTargetDate```, if both are configured, it will enter daily mode.

you can leave the rest as default value from original file.

Then edit job-config.json to match the running env. Then run that jar using this command :
```
# Run with terminal always online
yarn jar XXXX-jar-with-dependencies.jar job-config.json

# Run in background mode
nohup yarn jar XXXX-jar-with-dependencies.jar job-config.json &> yarn.log &
```

### Extract result
run this command to extract the result from HDFS : 
```shell script
hadoop fs -getmerge {HDFS_PATH from json} ./result.txt
```
the result.txt will appear from merging results from HDFS.

### Sample file format
```text
107-050801010054-DR 1000.0
107-050801010054-CR	1000.0
```