FROM  stackbrew/ubuntu:14.04

RUN apt-get update -y
RUN apt-get install -y git
RUN apt-get install -y wget

# Install Java.

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

ENV JAVA_VER 8
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

RUN update-java-alternatives -s java-8-oracle
RUN echo "export JAVA_HOME=/usr/lib/jvm/java-8-oracle" >> ~/.bashrc

# Install maven 3.3.9
RUN wget --no-verbose -O /tmp/apache-maven-3.3.9-bin.tar.gz http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz && \
    tar xzf /tmp/apache-maven-3.3.9-bin.tar.gz -C /opt/ && \
    ln -s /opt/apache-maven-3.3.9 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/local/bin  && \
    rm -f /tmp/apache-maven-3.3.9-bin.tar.gz

ENV MAVEN_HOME /opt/maven

EXPOSE 80 443

# Download repository
RUN git clone https://github.com/jiclotus/xmenrecruiter && cd xmenrecruiter && mvn compile && mvn package



