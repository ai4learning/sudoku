#!/bin/bash
usage() 
{ 
cat <<HELP
./deploy_admin.sh [development|production] [options]
development : deploy test enviroment
production  : deploy a production enviroment
-f : forcely restart admin process 
HELP
exit 1 
}

nargs=$# 
force_flag=0
build_mode=development

if [ $nargs -lt 1 ] || [ $nargs -gt 3 ] ; then
  usage
fi

if [ $nargs = 2 ] ; then
 if [ $2 = -f ] ; then
  force_flag=1
 else
  usage
 fi
fi

case $1 in
 "development")
    echo "development..."
    build_mode="development"
    ;;
 "production")
    echo "production..."
    build_mode="production"
    ;;
  *)
    usage
    ;; 
esac

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
 if [ $force_flag = 0 ] ; then
  echo "Already up-to-date,ready to exitng without restarting service"
  exit 0
 else
  echo "Already up-to-date,restart service by force"
 fi
fi
echo "Ready to fetch lastest code"

echo "Rebuilding project.."
mvn clean package -Dmaven.test.skip=true -P$build_mode
sh "$target_dir/word-study-admin/bin/shutdown.sh"

echo "Backing up existing version and deploying to target dir"
cp "$target_dir/word-study-admin/webapps/word-study-web-admin-0.0.1-SNAPSHOT.war" /home/back -rf
cp "$src_dir/word-study-web-admin/target/word-study-web-admin-0.0.1-SNAPSHOT.war" "$target_dir/word-study-admin/webapps/" -rf

echo "Display packages deployed"
ls -lh $target_dir/word-study-admin/webapps/word-study-web-admin-0.0.1-SNAPSHOT.war
sh "$target_dir/word-study-admin/bin/startup.sh"
