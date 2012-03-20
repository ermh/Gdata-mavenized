groupId=com.github.ermh.gdata
repoUrl=http://ccim4.local:8844/nexus/content/repositories/thirdparty
repoId=thirdparty

command="mvn deploy:deploy-file -Durl=${repoUrl} -DrepositoryId=${repoId}"
# command=mvn install:install-file 

lib="lib"
poms="poms"

function deploy {
	artifactId="$1"
	version="$2"

	mainpom="${poms}/${artifactId}-${version}.pom"
	cp "$mainpom" /tmp/main-$$.pom
	ed /tmp/main-$$.pom <<END
%s!<groupId>com.github.ermh.google.gdata</groupId>!<groupId>${groupId}</groupId>!
w
q
END
	$command \
		-Dfile=${lib}/${artifactId}-${version}.jar \
		-Dsources=${lib}/${artifactId}-sources-${version}.jar \
		-DpomFile=/tmp/main-$$.pom
		rm -f "/tmp/main-$$.pom"

	metapom="${poms}/${artifactId}-meta-${version}.pom"
	if [ -f "$metapom" ]; then
		cp "$metapom" /tmp/meta-$$.pom
		ed /tmp/meta-$$.pom <<END
%s!<groupId>com.github.ermh.google.gdata</groupId>!<groupId>${groupId}</groupId>!
w
q
END
	$command -Dfile=${lib}/${artifactId}-meta-${version}.jar \
		-DpomFile=/tmp/meta-$$.pom
	rm -f "/tmp/meta-$$.pom"
	else
		echo no pom $metapom 
	fi

}

deploy  "gdata-analytics" "2.1" 
deploy  "gdata-appsforyourdomain" "1.0" 
deploy  "gdata-base" "1.0" 
deploy  "gdata-blogger" "2.0" 
deploy  "gdata-blogger-meta" "2.0" 
deploy  "gdata-books" "1.0" 
deploy  "gdata-calendar" "2.0" 
deploy  "gdata-client" "1.0" 
deploy  "gdata-codesearch" "2.0" 
deploy  "gdata-contacts" "3.0" 
deploy  "gdata-core" "1.0" 
deploy  "gdata-docs" "3.0" 
deploy  "gdata-finance" "2.0" 
deploy  "gdata-gtt" "2.0" 
deploy  "gdata-health" "2.0" 
deploy  "gdata-maps" "2.0" 
deploy  "gdata-media" "1.0" 
deploy  "gdata-photos" "2.0" 
deploy  "gdata-projecthosting" "2.1" 
deploy  "gdata-sidewiki" "2.0" 
deploy  "gdata-sites" "2.0" 
deploy  "gdata-spreadsheet" "3.0" 
deploy  "gdata-webmastertools" "2.0" 
deploy  "gdata-youtube" "2.0" 
