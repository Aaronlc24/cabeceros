<%--
  Created by IntelliJ IDEA.
  User: Aaron
  Date: 10/11/2025
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login con Cookies</title>
    <style>
        /* Fondo general */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #6dd5ed, #2193b0);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Contenedor del login */
        .login-container {
            background-color: #ffffff;
            padding: 40px 50px;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
            width: 350px;
            text-align: center;
        }

        /* Título */
        h2 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        /* Campos de entrada */
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            margin: 8px 0 20px 0;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 14px;
            transition: all 0.3s ease;
        }

        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #2193b0;
            outline: none;
            box-shadow: 0 0 5px rgba(33,147,176,0.5);
        }

        /* Botón */
        button {
            background-color: #2193b0;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            font-size: 15px;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        button:hover {
            background-color: #186a8c;
        }

        /* Enlace */
        a {
            display: inline-block;
            margin-top: 15px;
            color: #2193b0;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Ícono */
        .icon {
            font-size: 50px;
            color: #2193b0;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div class="login-container">
    <div class="icon">🔐</div>
    <h2>Iniciar sesión</h2>

    <form action="login" method="post">
        <input type="text" name="usuario" placeholder="Usuario" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <button type="submit">Ingresar</button>
    </form>

    <a href="productos.html">Ver productos sin login</a>
</div>

</body>
</html>

