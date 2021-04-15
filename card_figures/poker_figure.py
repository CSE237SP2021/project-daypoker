def ascii_card(value, color):
    fname = 'Figure' + '_' + color + '_' + value + '.txt'
    f1 = open(fname, "w")
    if  color == "Club":
        f1.write(' _________ \n')
        if value != '10':
            f1.write('|' + value + '        ' + '|\n')
        elif value == '10':
            f1.write('|' + value + '       ' + '|\n')
        f1.write('|    _    |\n')
        f1.write('|  ( . )  |\n')
        f1.write('| (_____) |\n')
        f1.write('|    |    |\n')
        f1.write('|_________|')
    elif  color == "Heart":
        f1.write(' _________ \n')
        if value != '10':
            f1.write('|' + value + '        ' + '|\n')
        elif value == '10':
            f1.write('|' + value + '       ' + '|\n')
        f1.write('|  __ __  |\n')
        f1.write('| (  v  ) |\n')
        f1.write('|  \   /  |\n')
        f1.write('|    v    |\n')
        f1.write('|_________|')
    elif color == "Spade":
        f1.write(' _________ \n')
        if value != '10':
            f1.write('|' + value + '        ' + '|\n')
        elif value == '10':
            f1.write('|' + value + '       ' + '|\n')
        f1.write('|    ^    |\n')
        f1.write('|  /   \  |\n')
        f1.write('| (__.__) |\n')
        f1.write('|    |    |\n')
        f1.write('|_________|')
    elif color == "Diamond":
        f1.write(' _________ \n')
        if value != '10':
            f1.write('|' + value + '        ' + '|\n')
        elif value == '10':
            f1.write('|' + value + '       ' + '|\n')
        f1.write('|    ^    |\n')
        f1.write('|  /   \  |\n')
        f1.write('|  \   /  |\n')
        f1.write('|    v    |\n')
        f1.write('|_________|')
    f1.close()


value_list = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
color_list = ['Club', 'Heart', 'Spade', 'Diamond']
for color in color_list:
    for value in value_list:
        ascii_card(value, color)