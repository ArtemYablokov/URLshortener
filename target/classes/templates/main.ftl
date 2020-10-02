<#import "parts/common.ftl" as c>
<#--<#import "parts/loginform.ftl" as l>-->

<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/mess" method="get" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Filter">
                <#-- ! - если существует или ?ifExist -->
                <#--    <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                <button type="submit" class="btn btn-primary ml-2">Filter</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse" id="collapseExample"> <#-- ID связывает схлопывающуюся форму и кнопку-->
        <div class="form-group mt-3">
            <form action="/main" method="post" enctype="multipart/form-data"> <#-- ЭНКРИПТ для файла -->
                <div class="form-group col-md-6">
                    <input type="text" class="form-control" name="text" placeholder="Message">
                </div>
                <div class="form-group col-md-6">
                    <input type="text"  class="form-control" name="tag" placeholder="Tag">
                </div>
<#--                <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                <div class="form-group col-md-6">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <button type="submit" class="btn btn-primary">Добавить смс</button>
                </div>
            </form>
        </div>
    </div>

<div class="card-columns">
    <#list listOfMessages as message>
        <div class="card my-3"> <#-- отступ сверху/снизу. НЕ указываем ШИРИНУ карточек тк она указана во внешнем class="container" -->
            <#if message.filename??> <#-- если существует поле  ?? - приаодит к булевому типу -->
                <img src="/img/${message.filename}" class="card-img-top"> <#-- в img храним файлы -->
            </#if>
            <div class="m-2"> <#--отступ с четырех сторон -->
                <span>${message.text}</span>
                <i>${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                ${message.authorName}
            </div>

        </div>
    <#else>
        No message
    </#list>
</div>

<#--    <span><a href="/user">User list</a></span>-->
<#--    <@l.logout/>-->
</@c.page>