FROM ubuntu
RUN apt-get update
RUN apt-get install -y default-jre
RUN git clone https://github.com/paulochiong/walmart-back.git
RUN cd walmart-back
