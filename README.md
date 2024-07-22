# Java_HTTPServer
import matplotlib.pyplot as plt
import numpy as np

# Sample data
categories = ['A', 'B', 'C', 'D', 'E']
values1 = [100000, 200000, 300000, 400000, 500000]  # Values up to 1 million
values2 = [1, 2, 3, 4, 5]  # Values up to 10

# Create a figure and subplots
fig, ax1 = plt.subplots()

# Calculate the width of each bar and positions for each group
width = 0.4  # Width of each bar
x = np.arange(len(categories))  # The label locations

# Plot the first dataset on the primary y-axis
bars1 = ax1.bar(x - width/2, values1, width, label='Dataset1', color='tab:blue')
ax1.set_xlabel('Categories')
ax1.set_ylabel('Dataset1', color='tab:blue')
ax1.tick_params(axis='y', labelcolor='tab:blue')

# Create a secondary y-axis
ax2 = ax1.twinx()
bars2 = ax2.bar(x + width/2, values2, width, label='Dataset2', color='tab:red')
ax2.set_ylabel('Dataset2', color='tab:red')
ax2.tick_params(axis='y', labelcolor='tab:red')

# Set x-ticks and labels
ax1.set_xticks(x)
ax1.set_xticklabels(categories)

# Add a title
plt.title('Bar Charts with Different Scales')

# Add legends
ax1.legend(loc='upper left')
ax2.legend(loc='upper right')

# Show the plot
plt.show()
