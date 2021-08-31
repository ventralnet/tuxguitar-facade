@ECHO OFF
call mvn install:install-file -Dfile=lib/swt.jar -DgroupId=com.kirkley.deps -DartifactId=swt -Dversion=1.0 -Dpackaging=jar

call mvn install:install-file -Dfile=lib/tuxguitar-ascii.jar -DgroupId=com.kirkley.deps -DartifactId=tuxguitar-ascii -Dversion=1.0 -Dpackaging=jar

call mvn install:install-file -Dfile=lib/tuxguitar-gtp.jar -DgroupId=com.kirkley.deps -DartifactId=tuxguitar-gtp -Dversion=1.0 -Dpackaging=jar

call mvn install:install-file -Dfile=lib/tuxguitar.jar -DgroupId=com.kirkley.deps -DartifactId=tuxguitar -Dversion=1.0 -Dpackaging=jar

call mvn install:install-file -Dfile=lib/tuxguitar-ptb.jar -DgroupId=com.kirkley.deps -DartifactId=tuxguitar-ptb -Dversion=1.0 -Dpackaging=jar