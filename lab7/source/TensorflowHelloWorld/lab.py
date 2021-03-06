
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
rng=np.random
boston = load_boston()
data = np.array(boston.data)
print("**************************************")
print(boston.feature_names)
print("**************************************")
print(boston.DESCR)
print("**************************************")
labels = np.array(boston.target)
data=data[:,0]


#trX=data[0:200,0]
#trY=labels[0:200]
#tstX=data[200:400,0]
#tstY=labels[200:400]

trX,tstX,trY,tstY = train_test_split(data,labels , test_size=0.33, random_state=42)

X = tf.placeholder("float")

Y = tf.placeholder("float")

# create a shared variable for the weight matrix

w = tf.Variable(rng.randn(), name="weights")



b = tf.Variable(rng.randn(), name="bias")

b_plot=b

# prediction function
y_model = tf.add(tf.multiply(X, w), b)


# Mean squared error

cost = tf.reduce_sum(tf.pow(y_model-Y, 2))/(2*100)

# construct an optimizer to minimize cost and fit line to my data

train_op = tf.train.GradientDescentOptimizer(0.1).minimize(cost)


# Launch the graph in a session
sess = tf.Session()

# Initializing the variables

init = tf.global_variables_initializer()


# you need to initialize variables
sess.run(init)
w_plot=sess.run(w)
print(trX.shape,trY.shape)
for i in range(450):
    for (x, y) in zip(trX, trY):
        sess.run(train_op, feed_dict={X: x, Y: y})

print("Optimization Finished!")

training_cost = sess.run(cost, feed_dict={X: trX, Y: trY})

print("Training cost=", training_cost, "W=", sess.run(w), "b=", sess.run(b), '\n')

# Testing or Inference
#test_X = np.asarray([rng.randn(),rng.randn()])
#test_Y = 2*test_X + 4

print("Testing... (Mean square loss Comparison)")

testing_cost = sess.run(
    tf.reduce_sum(tf.pow(y_model - Y, 2)) / (2 * tstX.shape[0]),

    feed_dict={X: tstX, Y: tstY})  # same function as cost above

print("Testing cost=", testing_cost)
print("Absolute mean square loss difference:", abs(
    training_cost - testing_cost))

plt.plot(trX,trY,'g^')
plt.plot(trX, sess.run(w) * trX + sess.run(b), label='Fitted Line')
plt.xlabel(' per capita crime rate by town')
plt.ylabel('Median value of owner-occupied homes in $1000')
plt.title('Boston House Price prediction')
plt.show()


plt.plot([sess.run(b_plot),b], [w_plot,w],"o")
plt.axis([-1,1, -1,1])
plt.xlabel('b_update')
plt.ylabel('w_update')
plt.show()