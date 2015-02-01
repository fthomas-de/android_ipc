#!/usr/bin/env python
from subprocess import Popen, PIPE
import os, time

#writes content into file_name
def write_to_file(content, file_name):
	prefix = './epicc_out/'
	suffix = '.txt'
	f = open(prefix + file_name + suffix,'w')
	f.write(content)
	print '[Tool] writing: ' + file_name + suffix
	f.close() 

#parse epicc output and find ICC hints
def parse(epicc_file):
	#TODO
	pass

#starts the epicc analysis
def initialize(debug=False):
    pass
    print '[Tool] starting...'
    print '[Tool]'
    count = 0
    err = 0
    done = False
    for root, dirs, files in os.walk("./apps"):
        for file in files:
            if file.endswith(".sh") and 'epicc' in file:
                file_path = os.path.join(root, file)
                file_short = file_path[7:]
                file_name = file_short.split('/')[0]
                print '[Tool] runnig: ' + file_short

                t_1 = time.time()

                p = Popen(['/usr/bin/bash', file_path], stdout=PIPE, stderr=PIPE)
                res = p.stdout.read()
                return_value = p.wait()

                if return_value == 0:
                    count += 1
                    write_to_file(res, file_name)
                else:
                    err += 1

                t_2 = time.time()

                print '[Tool] finished after ' + str(int(t_2 - t_1)) + ' seconds'
                print '[Tool]'

                if debug:
                	done = True
                	break
            if debug and done:
                break

    print '[Tool] overall: ' + str(count) + ' successful scripts (' + str(err) + ' error(s))'

if __name__ == "__main__":
    main(debug=False)