status=("online" "offline")
student_ip=`grep '3306' /opt/word-study/webapps/ROOT/WEB-INF/classes/spring/spring-config-datasource.xml | awk -F:3306 '{print $1}' | awk -F// '{print $2}'` 
n=`netstat -an | grep 8086`
i=$?
echo "student web system database : ${status[$i]}"
echo $student_ip

admin_ip=`grep '3306' /opt/word-study-admin/webapps/ROOT/WEB-INF/classes/spring/spring-config-datasource.xml | awk -F:3306 '{print $1}' | awk -F// '{print $2}'` 
n=`netstat -an | grep 8088`
i=$?
echo "administrator web system database : ${status[$i]}"
echo $admin_ip
worker_ip=`grep '3306' /opt/word-study-worker/webapps/ROOT/WEB-INF/classes/spring/spring-config-datasource.xml | awk -F:3306 '{print $1}' | awk -F// '{print $2}'` 
n=`netstat -an | grep 8089`
i=$?
echo "worker web system database : ${status[$i]}"
echo $worker_ip
