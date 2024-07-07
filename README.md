<h1 align="center">FrankApi-SDK</h1>
<p align="center"><strong>FrankApi 接口开放平台 Java-sdk</strong></p>

<div align="center">
    <img alt="Maven" src="https://raster.shields.io/badge/Maven-3.8.1-red.svg"/>
   <img alt="SpringBoot" src="https://raster.shields.io/badge/SpringBoot-2.7.2-green.svg"/>
  <a href="https://github.com/ypclove/FrankApi-SDK" target="_blank"><img src='https://img.shields.io/github/forks/ypclove/FrankApi-SDK' alt='GitHub forks' class="no-zoom"></a>
  <a href="https://github.com/ypclove/FrankApi-SDK" target="_blank"><img src='https://img.shields.io/github/stars/ypclove/FrankApi-SDK' alt='GitHub stars' class="no-zoom"></a>
</div>
## 导航

* **[FrankApi 接口开放平台 🔗](https://api.franksteven.me/)**
* **[FrankApi-Backend 后端 👨‍💻](https://github.com/ypclove/FrankApi-Backend)**
* **[FrankApi-Frontend 前端 🖥](https://github.com/ypclove/FrankApi-Frontend)**
* **[FrankApi-Doc 开发者文档 📖](https://doc.franksteven.me/)**
* **[FrankApi-SDK 🔭](https://github.com/ypclove/FrankApi-SDK)**
* **[FrankApi-SDK-Demo ✔️](https://github.com/ypclove/FrankApi-Backend/blob/master/frankapi-backstage/src/main/java/com/frank/apibackstage/controller/InterfaceInfoController.java)**

## 快速开始

### 1. 添加依赖

在您的项目 pom.xml 中添加如下依赖：

```xml
<dependency>
    <groupId>com.frank</groupId>
    <artifactId>FrankApi-SDK</artifactId>
    <version>0.0.1</version>
</dependency>
```

### 2. 获取并配置开发者密钥

* 前往 [FrankApi 接口开放平台](https://api.franksteven.com/)，注册并登录进入个人中心，即可获取到您的开发者密钥。

* 配置您的开发者密钥，在您的项目 application.yml（推荐） 或者 application.properties 中添加如下配置。

  1. yml

     ```yaml
     # FrankApi 配置
     frank:
       api:
         client:
           access-key: 你的 accessKey
           secret-key: 你的 secretKey
           # 本地网关地址，可修改为自己的网关，用于本地测试，线上网关地址等，不配置默认平台的网关
           # host: http://localhost:8090/api
     ```

  2. properties

     ```properties
     frank.api.client.access-key=你的 accessKey
     frank.api.client.secret-key=你的 secretKey
     ```

### 3. 使用 FrankApiClient 调用接口

* 请求示例

  ```java
  @Resource
  private ApiService apiService;
  
  @Resource
  private FrankApiClient frankApiClient;
  
  try {
      CurrencyRequest currencyRequest = new CurrencyRequest();
      currencyRequest.setMethod(interfaceInfo.getMethod());
      currencyRequest.setPath(interfaceInfo.getUrl());
      currencyRequest.setRequestParams(params);
      ResultResponse response = apiService.request(frankApiClient, currencyRequest);
      return response.getData();
  } catch (Exception e) {
      log.error("调用接口失败: {}", e.getMessage());
      throw new BusinessException(StatusCode.SYSTEM_ERROR, e.getMessage());
  }
  ```

* 响应示例

  ```json
  {
      "code": 20000,
      "data": {
          "city": "北京市",
          "data": {
              "date": "2024-07-07",
              "week": "星期日",
              "type": "阴",
              "low": "24°C",
              "high": "32°C",
              "fengxiang": "东风",
              "fengli": "1-3级",
              "night": {
                  "type": "小雨",
                  "fengxiang": "北风",
                  "fengli": "1-3级"
              }
          },
          "air": {
              "aqi": 87,
              "aqi_level": 2,
              "aqi_name": "良",
              "co": "1",
              "no2": "10",
              "o3": "189",
              "pm10": "47",
              "pm2.5": "28",
              "so2": "3"
          },
          "tip": "天太热了，吃个西瓜~"
      },
      "msg": "success"
  }
  ```

* **更多示例详见：[FrankApi-SDK-Demo 示例项目](https://github.com/ypclove/FrankApi-Backend/blob/master/frankapi-backstage/src/main/java/com/frank/apibackstage/controller/InterfaceInfoController.java)**
* **更多接口详见：[FrankApi-Doc 开发者文档](https://doc.franksteven.me/)**

## 贡献

如果您想为 **[FrankApi 接口开放平台](https://api.franksteven.me/)**  做出贡献，请随时提交拉取请求。我们始终在寻找方法来改进项目。

## issue

如果您有本项目的任何问题，或者发现了某些:bug:，欢迎 issue。
