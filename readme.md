Ejercicio Consulta Directores Cine:

Diseñar una aplicación que consulte en una base de datos los títulos de las películas dirigidas por un determinado director de cine. Para ello la aplicación mostrará una página inicial con un formulario en el que se introducirá el nombre del director de cine, y tras pulsar un botón consultar, se dirigirá la petición a un servlet que validará y obtendrá el listado de películas de ese director. Una vez obtenida esa lista, el servlet la mostrará al usuario, dándole la opción de realizar otra consulta. Si se responde que sí, se vuelve al formulario y se procede de la misma manera. En la página que se muestra la lista de películas, también se ofrece un botón finalizar. Si se pulsa, se mostrará la lista de nombres de directores consultados. En esa página habrá un bóton para volver al formulario inicial y realizar nuevas consultas, pero iniciando una sesión nueva. Si se introduce un nombre de director no existente, se informará de ello y se ofrecerá de nuevo el formulario para una nueva consulta.

Nombre de la base de datos: cine

Tabla: peliculas

Campos: director(VARCHAR(128)), titulo(VARCHAR(128)),fecha(DATETIME)

Nombre Aplicación (context root): filmografia

Datasource: dscine

DataSource:

                <datasource jta="true" jndi-name="java:jboss/datasources/dscine" pool-name="dscine" enabled="true" use-java-context="true" use-ccm="true">
                    <connection-url>jdbc:mysql://localhost:3306/cine</connection-url>
                    <driver>mysql5</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mysql</password>
                    </security>
                </datasource>