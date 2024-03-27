# Spring AI 实现朴素 RAG

## 运行

需要在本地安装 Ollama 和 Docker Desktop。使用 Docker Desktop 来运行
Redis。参考 `docker-compose.yaml` 文件。

使用 `dev` profile 来运行 Spring。

访问 [Swagger UI](http://localhost:8080/swagger-ui/index.html) 来与 API 交互。

## 加载网页到向量数据库

发送 POST 请求到 `/etl/webpage`，提供网页 URL。

```json
{
  "url": "https://www.gov.cn/yaowen/liebiao/202403/content_6939153.htm"
}
```

## 与大模型交互

发送 POST 请求到 `/chat/query`，提供消息内容。

```json
{
  "message": "从2024年政府工作报告中，分析人工智能的发展前景"
}
```

## 课程

[![课程](./course.png)](https://www.bilibili.com/cheese/play/ss15645)

《AI 大模型检索增强生成【Spring AI
实现】》课程已经上线，可以从 [B站](https://www.bilibili.com/cheese/play/ss15645)
或 [抖音](https://v.douyin.com/iF7jm8Nu/) 购买。