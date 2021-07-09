ps -ef | grep -w 6379 | grep redis | awk '{
exist=1
pid=$2
print "kill process "pid
if (system("kill -9 " pid)) print "stop failed" | "cat 1>&2"
else print "stop succeed"
}
END{if(!exist) print "process not exist" | "cat 1>&2"
}' exist=0
