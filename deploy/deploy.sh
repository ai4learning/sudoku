#!/bin/bash

src_dir=/home/sudoku/sudoku_ai4learning/src
target_dir=/opt

cd $src_dir/
echo "Entering working dir dir $PWD"
echo "Trying to pull the lastest code from branch develop"
ret=`git pull origin develop`
parser="Already up-to-date"
len=${#parser}
if [ "$parser" == "${ret:0:$len}" ]
then
 echo "Already up-to-date,exting" 
 exit 0
fi
echo "Ready to fetch lastest code"

echo "Rebuilding project.."
mvn clean package -Dmaven.test.skip=true -Pproduction

sh "$target_dir/word-study/bin/shutdown.sh"
sh "$target_dir/word-study-admin/bin/shutdown.sh"
sh "$target_dir/word-study-worker/bin/shutdown.sh"

echo "Backing up existing version and deploying to target dir"
cp "$target_dir/word-study/webapps/word-study-web-0.0.1-SNAPSHOT.war" /home/back/ -rf
cp "$target_dir/word-study-admin/webapps/word-study-web-admin-0.0.1-SNAPSHOT.war" /home/back -rf
cp "$target_dir/word-study-worker/webapps/word-study-web-worker-0.0.1-SNAPSHOT.war" /home/back	-rf
cp "$src_dir/word-study-web/target/word-study-web-0.0.1-SNAPSHOT.war" "$target_dir/word-study/webapps/" -rf
cp "$src_dir/word-study-web-admin/target/word-study-web-admin-0.0.1-SNAPSHOT.war" "$target_dir/word-study-admin/webapps/" -rf
cp "$src_dir/word-study-web-worker/target/word-study-web-worker-0.0.1-SNAPSHOT.war"  "$target_dir/word-study-worker/webapps/" -rf

echo "Display packages deployed"
ls -lh $target_dir/word-study/webapps/word-study-web-0.0.1-SNAPSHOT.war
ls -lh $target_dir/word-study-admin/webapps/word-study-web-admin-0.0.1-SNAPSHOT.war
ls -lh $target_dir/word-study-worker/webapps/word-study-web-worker-0.0.1-SNAPSHOT.war
sh "$target_dir/word-study/bin/startup.sh"
sh "$target_dir/word-study-admin/bin/startup.sh"
sh "$target_dir/word-study-worker/bin/startup.sh"
