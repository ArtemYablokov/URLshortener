<#import "parts/common.ftl" as c>

<@c.page>
    <div>URL</div>
    <div>${allert!}</div>
    <form action="/main" method="post">
        <input type="text" name="userUrl"
               placeholder="insert URL"
               value="${userUrl!}">
        <br>
        <input type="submit">
        <#if shortUrl??>
            <input type="text" name="shortUrl"
                   placeholder=""
                   value="${shortUrl}">
        </#if>
    </form>

    <form class="form-style-7">
        <ul>
            <li>
                <label for="name">Name</label>
                <input type="text" name="name" maxlength="100">
                <span>Enter your full name here</span>
            </li>
            <li>
                <label for="email">Email</label>
                <input type="email" name="email" maxlength="100">
                <span>Enter a valid email address</span>
            </li>
            <li>
                <label for="url">Website</label>
                <input type="url" name="url" maxlength="100">
                <span>Your website address (eg: http://www.google.com)</span>
            </li>
            <li>
                <label for="bio">About You</label>
                <textarea name="bio" onkeyup="adjust_textarea(this)"></textarea>
                <span>Say something about yourself</span>
            </li>
            <li>
                <input type="submit" value="Send This">
            </li>
        </ul>
    </form>
</@c.page>