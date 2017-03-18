import tensorflow as tf
import numpy as np
import pickle


data_path = "/home/prudhvi/Desktop/lab 8/MNIST_SOFTMAX/data_new/CIFAR-10/data_batch_"

for i in range(1,5):
 data_path=data_path+str(i)
 fo = open(data_path, 'rb')
 fo.seek(0) #offset of the file to beginning
 dict_ = (pickle.load(fo,encoding='bytes'))
 labels = ((labels, np.array(dict_[b'labels'])))
 data = np.vstack((data,np.array(dict_[b'data'])))
fo.close()



