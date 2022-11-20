<?xml version="1.0" encoding="UTF-8"?>

<!--
	Document   : profil.xsl
	Created on : October 25, 2022, 5:34 PM
	Author     : trr
	Description:
		Purpose of transformation follows.
-->

<xsl:stylesheet 
	version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tux="http://myGame/tux">

	<xsl:output method="html"/>

	<!--
		* Afficher le nom, l'anniversaire, l'avatar du joueur
		* Créer un tableau contenant chaque partie du joueur
	-->
	<xsl:template match="/">
		<xsl:variable name="avatar" select="tux:profil/tux:avatar"/>

		<html>
			<head>
				<title>Profil</title>
				<link rel="stylesheet" href="../css/style.css"/>
			</head>
			<body>
				<h1>Profil</h1>
				<b>Nom</b> : <xsl:value-of select="tux:profil/tux:nom"/>
				<br/>
				<b>Anniversaire</b> : <xsl:value-of select="tux:profil/tux:anniversaire"/>
				<br/>
				<b>Avatar</b> : <xsl:value-of select="tux:profil/tux:avatar"/>
				<br/>

				<b>Parties</b> :
				<br/>
				<table>
					<tr>
						<th>DATE</th>
						<th>TEMPS</th>
						<th>TROUVE</th> 
						<th>MOT</th>
						<th>NIVEAU</th>
					</tr>
					<xsl:apply-templates select="tux:profil/tux:parties/tux:partie"/>
				</table>
			</body>
		</html>
	</xsl:template>

	<!--
		Pour chaque tux:partie : créer une ligne du tableau contenant :
		* la date de la partie
		* le temps mis pour trouvé le mot
			* si mon non trouvé alors afficher Perdu
		* le pourcentage le lettre trouvé dans le mot
		* le mot
		* le niveau du mot
	-->
	<xsl:template match="tux:profil/tux:parties/tux:partie">
		<tr>
			<td>
				<xsl:value-of select="@date"/>
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="tux:temps">
						<xsl:value-of select="tux:temps"/>s
					</xsl:when>
					<xsl:otherwise>Perdu</xsl:otherwise>
				</xsl:choose>
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="@trouvé">
						<xsl:value-of select="@trouvé"/>
					</xsl:when>
					<xsl:otherwise>100%</xsl:otherwise>
				</xsl:choose>
			</td>
			<td>
				<xsl:value-of select="tux:mot"/>
			</td>
			<td>
				<xsl:value-of select="tux:mot/@niveau"/>
			</td>
		</tr>
	</xsl:template>
	
</xsl:stylesheet>
	