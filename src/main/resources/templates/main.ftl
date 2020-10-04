<#import "parts/common.ftl" as c>

<@c.page>
    <div>URL</div>
    <div>${allert!}</div>
    <form action="/main" method="post" class="form-style-7">
        <input type="text" name="userUrl"
               placeholder="insert URL"
               value="${userUrl!}"> <br>
        <input type="submit">

        <#if shortUrl??>
            <input type="text" name="shortUrl"
                   placeholder=""
                   value="${shortUrl}" width="50%">
        </#if>
    </form>

</@c.page>