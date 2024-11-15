$(document).ready(function () {
    listing();
});

function listing() {
    $.ajax({
        type: 'GET',
        url: '/api/v1/english-transcript',
        data: {},
        success: function (response) {
            renderTranscriptList(response);
        },
        error: function (error) {
            console.error('영어 스크립트를 가져오는 중 오류 발생:', error);
            alert('영어 스크립트를 가져오는 중 오류가 발생했습니다. 서버 상태를 확인하세요.');
        }
    });
}

function renderTranscriptList(transcriptList) {
    const $articlesBox = $('#articles-box');
    $articlesBox.empty();

    transcriptList.forEach((transcript) => {
        const { id, title, content, createdAt } = transcript;

        const truncatedContent = content.length > 100 ? content.substring(0, 100) + '...' : content;
        const formattedDate = new Date(createdAt).toLocaleDateString();

        const htmlData = `
        <div class="col">
            <div class="article-card h-100">
                <img src="/images/announce/postIsNotReady.png" class="article-img-top" alt="Article Image">
                <div class="article-body">
                    <a href="detail.html?id=${id}">
                        <h5 class="article-title">${title}</h5>
                    </a>
                    <p class="article-text">${truncatedContent}</p>
                    <p class="article-date">게시일: ${formattedDate}</p>
                </div>
            </div>
        </div>`;

        $articlesBox.append(htmlData);
    });
}
