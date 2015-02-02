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

#runs dare before strting with epicc
def run_dare(debug=False):
	print '[Dare] running: all dare scripts'
	t_1 = time.time()
	succ = 0
	err = 0
	done = False
	for root, dirs, files in os.walk("./apps"):
		for file in files:
			if file.endswith(".sh") and 'dare' in file:
				file_path = os.path.join(root, file)
				p = Popen(['/usr/bin/bash', file_path], stdout=PIPE)
				res = p.stdout.read()
				return_value = p.wait()
				if return_value == 0:
					succ+=1
				else:
					err+=1

				if debug:
					done = True
					break
			if debug and done:
				break

	t_2 = time.time()
	print '[Dare] finished after ' + str(int(t_2 - t_1)) + ' seconds'
	print '[Dare] overall: ' + str(succ) + ' successful scripts (' + str(err) + ' error(s))'
	print '[----]'

#starts the epicc analysis
def initialize(runDare=False, debug=False, benchmark=None):
    pass
    print '[Tool] starting: runDare=' + str(runDare) + ', debug=' + str(debug) + ', benchmark=' + benchmark
    print '[----]'

    if runDare: run_dare(debug)

    succ = 0
    err = 0
    done = False
    for root, dirs, files in os.walk("./apps"):
        for file in files:
            if file.endswith(".sh") and 'epicc' in file:
                file_path = os.path.join(root, file)
                file_short = file_path[7:]
                file_name = file_short.split('/')[0]
                
                if not benchmark in file_short: break
                print '[Epic] runnig: ' + file_short

                t_1 = time.time()

                p = Popen(['/usr/bin/bash', file_path], stdout=PIPE, stderr=PIPE)
                res = p.stdout.read()
                return_value = p.wait()

                if return_value == 0:
                    succ += 1
                    write_to_file(res, file_name)
                else:
                    err += 1

                t_2 = time.time()

                print '[Epic] finished after ' + str(int(t_2 - t_1)) + ' seconds'
                print '[----]'

                if debug:
                	done = True
                	break
            if debug and done:
                break

    print '[Epic] overall: ' + str(succ) + ' successful scripts (' + str(err) + ' error(s))'

if __name__ == "__main__":
	#TODO parse input
	initialize(runDare=True, debug=True, benchmark="SendBroadcast")
	#BroadcastReceiver
	#SendBroadcast
