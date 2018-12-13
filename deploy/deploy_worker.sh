#!/bin/bash
usage() 
{ 
cat <<HELP
./deploy_admin.sh [options]
-f : forcely restart admin process) 
HELP
exit 1 
}

nargs=$# 
if [ $nargs -gt 0 ] && [ ! $1 = -f ] ; then 
	 usage 
fi

src_dir=/home/sudoku/src
target_dir=/opt

cd $src_dir/
echo "Entering working dir dir $PWD"
echo "Trying to pull the lastest code from branch develop"
ret=`git pull origin develop`
parser="Already up-to-date"
len=${#parser}
if [ "$parser" == "${ret:0:$len}" ]
then
 if [ $nargs -eq 0 ] ; then
  echo "Already up-to-date,ready to exitng without restarting service"
  exit 0
 else
  echo "Already up-to-date,restart service by force"
 fi
fi
echo "Ready to fetch lastest code"

echo "Rebuilding project.."
mvn clean package -Dmaven.test.skip=true -Pdevelopment

sh "$target_dir/word-study-worker/bin/shutdown.sh"

echo "Backing up existing version and deploying to target dir"
cp "$target_dir/word-study-worker/webapps/word-study-web-worker-0.0.1-SNAPSHOT.war" /home/back	-rf
cp "$src_dir/word-study-web-worker/target/word-study-web-worker-0.0.1-SNAPSHOT.war"  "$target_dir/word-study-worker/webapps/" -rf

echo "Display packages deployed"
ls -lh $target_dir/word-study-worker/webapps/word-study-web-worker-0.0.1-SNAPSHOT.war
sh "$target_dir/word-study-worker/bin/startup.sh"
