FROM python:3.11

WORKDIR /producer-python

ENV BROKER_NAME=broker-1
ENV QUEUE_NAME=cola1

COPY producer.py .

RUN pip install --no-cache-dir Flask==2.3.3 pika==1.3.1

EXPOSE 8081

CMD ["python", "/producer-python/producer.py"]