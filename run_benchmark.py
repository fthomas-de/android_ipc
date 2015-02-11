#!/usr/bin/env python
from subprocess import Popen, PIPE
import os, time

# writes content into file_name
def write_to_file(content, file_name):
    prefix = './epicc_out/'
    suffix = '.txt'
    f = open(prefix + file_name + suffix, 'w')
    f.write(content)
    print '[Tool] writing: ' + file_name + suffix
    f.close()


# parse epicc output and find ICC hints
def parse(epicc_file):
    #TODO
    pass


#runs dare before strting with epicc
def run_dare(debug=False, benchmark=None):
    print '[Dare] running: all dare scripts'
    t_1 = time.time()
    succ = 0
    err = 0
    done = False
    for root, dirs, files in os.walk("./apps"):
        for file in files:
            if file.endswith(".sh") and 'dare' in file:
                file_path = os.path.join(root, file)

                if benchmark != None:
                    if not benchmark in file_path: break

                p = Popen(['/usr/bin/bash', file_path], stdout=PIPE)
                res = p.stdout.read()
                return_value = p.wait()
                if return_value == 0:
                    succ += 1
                else:
                    err += 1

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
def initialize(rundare=False, debug=False, benchmark=None):
    pass
    print '[Tool] starting: rundare=' + str(rundare) + ', debug=' + str(debug) + ', benchmark=' + str(benchmark)
    print '[----]'

    if rundare:
        run_dare(debug, benchmark)

    succ = 0
    err = 0
    done = False
    for root, dirs, files in os.walk("./apps"):
        for file in files:
            if file.endswith(".sh") and 'epicc' in file:
                file_path = os.path.join(root, file)
                file_short = file_path[7:]
                file_name = file_short.split('/')[0]

                if benchmark != None:
                    if not benchmark in file_short: break
                print '[Epic] runnig: ' + file_short

                t_1 = time.time()

                p = Popen([file_path], stdout=PIPE, stderr=PIPE)
                res = p.stdout.read()
                res_tmp = res.split('\n')
                res_tmp_1 = filter(lambda k: 'Warning' not in k, res_tmp)
                res_tmp_2 = filter(lambda k: 'Transforming' not in k, res_tmp_1)
                res_tmp_3 = filter(lambda k: 'Call Graph' not in k, res_tmp_2)
                res_tmp_4 = filter(lambda k: 'Spark' not in k, res_tmp_3)
                res = ""

                for line in res_tmp_4:
                    res += line
                    res += '\n'

                return_value = p.wait()
                #print return_value

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
    #initialize(rundare=True, debug=False)
    #TODO parse input
    initialize(rundare=True, debug=False, benchmark="BroadcastReceiver3")
    #BroadcastReceiver
    #BroadcastReceiver2
    #BroadcastReceiver3
    #SendBroadcast
    #StartService
    #ACT_Android
    #StartActivity
    #PendingIntent
    #PendingIntent2
    #ACT
    #StartLinkedActivity
    #StartLinkedActivity2
    #ViewImageViaIntent
    #rasp
