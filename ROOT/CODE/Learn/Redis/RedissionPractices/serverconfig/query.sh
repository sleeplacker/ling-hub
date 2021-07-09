res=`ps -ef | grep -w 6379 | grep  redis | grep -v grep | awk '{print $2}'`
if [ "$res"x != ""x ]; then
   echo process $res started.
else
   echo process stopped.
fi

