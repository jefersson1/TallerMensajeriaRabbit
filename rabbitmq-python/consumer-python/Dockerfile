FROM python:3.11

WORKDIR /consumer-python

COPY consumer.py .

RUN pip install --no-cache-dir Flask==2.3.3 pika==1.3.1

EXPOSE 8082

CMD ["python", "/consumer-python/consumer.py"]