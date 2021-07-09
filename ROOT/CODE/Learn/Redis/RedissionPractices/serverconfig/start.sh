res=`ps -ef | grep -w 6379 | grep  redis | grep -v grep | awk '{print $2}'`

test "$res" && { echo "process $res already running" >&2; exit 1; }

redis-server redis-6379.conf --requirepass dbp12345

echo "process $! started"
