<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet 
	version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tux="http://myGame/tux">

	<xsl:output method="html"/>

	<!-- 
		* Afficher un titre pour la page
		* Créer un tableau contenant les mot de chaque niveau triés par ordre alphabétique
	-->
	<xsl:template match="/">
		<html>
			<head>
				<title>Dictionnaire Tux</title>
				<link rel="stylesheet" href="../css/style.css"/>
			</head>
			<body>
				<h1>Dictionnaire</h1>
				<table>
					<tr>
						<th>NIVEAU 1</th>
						<th>NIVEAU 2</th>
						<th>NIVEAU 3</th>
						<th>NIVEAU 4</th>
						<th>NIVEAU 5</th>
					</tr>
					<tr>
						<td>
							<xsl:apply-templates select="tux:dictionnaire/tux:mot[@niveau=1]">
								<xsl:sort select="." order="ascending"/>
							</xsl:apply-templates>
						</td>
						<td>
							<xsl:apply-templates select="tux:dictionnaire/tux:mot[@niveau=2]">
								<xsl:sort select="." order="ascending"/>
							</xsl:apply-templates>
						</td>
						<td>
							<xsl:apply-templates select="tux:dictionnaire/tux:mot[@niveau=3]">
								<xsl:sort select="." order="ascending"/>
							</xsl:apply-templates>
						</td>
						<td>
							<xsl:apply-templates select="tux:dictionnaire/tux:mot[@niveau=4]">
								<xsl:sort select="." order="ascending"/>
							</xsl:apply-templates>
						</td>
						<td>
							<xsl:apply-templates select="tux:dictionnaire/tux:mot[@niveau=5]">
								<xsl:sort select="." order="ascending"/>
							</xsl:apply-templates>
						</td>
					</tr>
				</table>
			</body>
		</html>
	</xsl:template>

	<!--
		Pour chaque tux:mot : afficher la valeur
	-->
	<xsl:template match="tux:dictionnaire/tux:mot">
		<xsl:value-of select="."/>
		<br/>
	</xsl:template>


</xsl:stylesheet>
