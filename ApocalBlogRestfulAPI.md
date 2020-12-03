# 教主小宇宙 RestfulAPI
## 功能
使得其他开发者可以通过调用本API，获取教主小宇宙的博客上的博文、评论，以及相关的时间戳等信息。
## 接口
### 访问
通过HTTP GET进行数据获取，通过URL传递需要获取的数据类型。
### 返回
回传JSON，包含访问状态（成功或失败），访问的对象类型，以及访问的对象body。body通过UTF-8编码。
当访问成功时，body为博文的正文。在访问失败时，body为错误原因。
#### JSON格式
```json
{
    "status": "success",
    "requested_object": "post", 
    "body": "hello world"
}
```
## 访问地址
| 功能 | 地址 |
| - | - |
| 博文 | https://eture.tech/apocal/api/post/ |
| 评论 | https://eture.tech/apocal/api/emotion/ |
# 实现
1. 提取URL当中的requested_object
	```python
	# urls.py

	from django.urls import path
	# ......

	from . import views

	urlpatterns = [
		# ......
		path('api/<str:requested_object>/', views.api, name='api'),
	]
	```
1. 对requested_object进行合法性判断
	```python
	# views.py

	# ......

	@csrf_exempt
	def api(request, requested_object):
		# ......
		if requested_object == 'post':
			# ......
		if requested_object == 'emotion':
			# ......
		# ......
	```
1. 对合法地request从数据库中查询相对应的object
    ```python
    Emotion.objects.filter(happy=True, message__isnull=False))
    ```
1. 进行序列化
    ```python
    response['body'] = serializers.serialize('xml', Emotion.objects.filter(happy=True, message__isnull=False))
    ```
1. 封装成JSON
    ```python
    response = {'status': 'failed', 'requested_object': requested_object, 'body': ''}
    # ......
    json.dumps(response)
    ```
1. HTTP返回
    ```python
    return HttpResponse(json.dumps(response))
    ```