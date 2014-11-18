<!DOCTYPE html>
<html>
	<head>
		<title>Best Deal</title>
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<script type="text/javascript" src="javascript/javascript.js"></script>
	</head>
	<body onload="init()">
		<header>
			<table border="1" width="100%">
				<tr>
					<td width="80%">
						<h1><a href="/ecom/HomePage">Best Deal</a></h1>
					</td>
					<td width="20%">
						<h4><a href="/ecom/CartPage">Cart</a></h4>
					</td>
				</tr>
			</table>
			<table border="1" width="100%">
				<tr>
					<td width="30%">
						<a href="#">Weekly Deals</a>
					</td>
					<td width="40%">
						<form name="autofillform" action="autocomplete">
						<table border="0" cellpadding="5">
							<tbody>
							<tr>
								<td><strong>Search:</strong></td>
											<td>
												<input type="text" size="40" id="complete-field" autocomplete="off" onkeyup="doCompletion()">
											</td>
							</tr>
							<tr>
								<td id="auto-row" colspan="2">
									<table id="complete-table" class="popupBox"></table>
								</td>
							</tr>
							</tbody>
						</table>
						</form>
					</td>
					<td width="30%">
						<a href="/ecom/SignInPage">Sign in</a>
					</td>
				</tr>
			</table>
		</header>

		<nav>
			<form action="/ecom/CatalogPage">
			<table>
				<tr>
					<td>
						<input id="button" type="submit" name ="product" VALUE="Phones">
					</td>
				</tr>
				<tr>
					<td>
						<input id="button" type="submit" name ="product" VALUE="Tablets">
					</td>
				</tr>
				<tr>
					<td>
						<input id="button" type="submit" name ="product" VALUE="Laptop">
					</td>
				</tr>
				<tr>
					<td>
						<input id="button" type="submit" name ="product" VALUE="TV">
					</td>
				</tr>
			</table>
			</form>
		</nav>

		<aside>
			<h1 align="center">Deals</h1>
		</aside>
	</body>
<html>
