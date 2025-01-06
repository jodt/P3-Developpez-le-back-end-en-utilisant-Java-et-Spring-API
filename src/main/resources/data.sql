USE chatop;

INSERT INTO USERS
VALUES (1,
        'userOne@gmail.com',
        'firstuser',
        '$2y$10$eKbdr.2p4by4l/0jMQAuwuZQO9u1sCamy7Rs.9idoqosqy4DRZJMS',
        NOW(),
        NOW()
        ),
       (2,
        'userTwo@gmail.com',
        'seconduser',
        '$2y$10$eKbdr.2p4by4l/0jMQAuwuZQO9u1sCamy7Rs.9idoqosqy4DRZJMS',
        NOW(),
        NOW()
       )
    ;

INSERT INTO RENTALS
VALUES (1,
        'test house 1',
        432,
        300,
        'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.',
        1,
        '2012/12/02',
        '2014/12/02'),
       (2,
        'test house 2',
        154,
        200,
        'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.',
        2,
        '2012/12/02',
        '2014/12/02'),
       (3,
        'test house 3',
        234,
        100,
        'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.',
        1,
        '2012/12/02',
        '2014/12/02');
